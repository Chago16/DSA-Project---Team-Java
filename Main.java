import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Main {

    public static void main(String[] args) {
        loadPoppinsFont();
        // Show the splash screen
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.showSplashScreen(3000); // Display for 3000 milliseconds (3 seconds)

        // Continue with your main application code
        SwingUtilities.invokeLater(() -> {
            Variables variablesFunc = new Variables();
            variablesFunc.updateFromFile("Data.dat");
        
            // creates a card layout for the mainPanel only start
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout); // instance of the main panel
        
            HomePage hp = new HomePage();
            mainPanel.add(hp.homePanel, "HomePage");
            IncomePage ip = new IncomePage();
            mainPanel.add(ip.incomePanel, "IncomePage");
            ExpensePage ep = new ExpensePage();
            mainPanel.add(ep.expensePanel, "ExpensePage");
            GoalPage gp = new GoalPage();
            mainPanel.add(gp.goalPanel, "GoalPage");
            TipsPage tp = new TipsPage();
            mainPanel.add(tp.tipsPanel, "TipsPage");
            AboutPage ap = new AboutPage();
            mainPanel.add(ap.aboutPanel, "AboutPage");
        
            // Update available balance before showing HomePage
            HomePage.updateAvailableBalance();
            GoalPage.updateSavings();
        
            cardLayout.show(mainPanel, "HomePage");

            // mainPanel instance end =====

            // menu panel instance start
            MenuPage mp = new MenuPage();
            JPanel menuPanel = mp.menuPanel;
            mp.setCardLayout(cardLayout, mainPanel); // experimental
            mp.addListeners();

            // menu panel instance end =====

            // creates the frame for the whole GUI
            JFrame mframe = new JFrame("Coffer Bliss");
            mframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            mframe.setSize(1000, 600);
            mframe.setLocationRelativeTo(null);
            mframe.add(mainPanel);

            mframe.add(mainPanel, BorderLayout.CENTER);
            mframe.add(menuPanel, BorderLayout.WEST);

            // Add window listener to handle window close operation
            mframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    showExitDialog(mframe);
                }
            });

            mframe.setVisible(true);

            // whole GUI end =====

            IncomePage.loadIncCSVToTable("IncomeTable.csv");
            ExpensePage.loadExpCSVToTable("ExpensesTable.csv");

            

            // Close the splash screen
            splashScreen.dispose();
        });
    }

    private static void loadPoppinsFont() {
        try {
            // Load Poppins font from the file
            Font poppinsFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Regular.ttf"));
    
            // Register the font globally
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(poppinsFont);
    
            // Set Poppins as the default font for Swing components
            setUIFont(new FontUIResource(poppinsFont));
        } catch (IOException | FontFormatException e) {
            // Handle the exception more gracefully, e.g., show an error dialog
            e.printStackTrace();
            // You might want to display an error dialog or log the error to a file
        }
    }
    

    private static void setUIFont(FontUIResource font) {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<?> keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = defaults.get(key);
            if (value instanceof FontUIResource) {
                defaults.put(key, font);
            }
        }
    }
    
    
private static void showExitDialog(JFrame parentFrame) {
    // Load Poppins font from the file
    Font poppinsFont;
    try {
        poppinsFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Regular.ttf"));
    } catch (IOException | FontFormatException e) {
        e.printStackTrace();
        poppinsFont = new Font("Arial", Font.PLAIN, 12); // Fallback font
    }

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JLabel label = new JLabel("Do you really want to exit?");
    label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    label.setFont(poppinsFont.deriveFont(Font.PLAIN, 14)); // Set font for the label

    panel.add(label, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JButton yesButton = new JButton("Yes");
    yesButton.setBackground(Color.decode("#DDDDD0"));
    yesButton.setForeground(Color.BLACK);
    yesButton.setFont(poppinsFont.deriveFont(Font.PLAIN, 14)); // Set font for the button

    JButton noButton = new JButton("No");
    noButton.setBackground(Color.decode("#FF914D"));
    noButton.setForeground(Color.WHITE);
    noButton.setFont(poppinsFont.deriveFont(Font.PLAIN, 14)); // Set font for the button

    noButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((JDialog) noButton.getTopLevelAncestor()).dispose();
        }
    });

    yesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });

    buttonPanel.add(yesButton);
    buttonPanel.add(noButton);

    panel.add(buttonPanel, BorderLayout.SOUTH);

    JDialog dialog = new JDialog(parentFrame, "Exit", true);
    dialog.getContentPane().add(panel);
    dialog.setSize(new Dimension(300, 150));
    dialog.setLocationRelativeTo(parentFrame);
    dialog.setVisible(true);
    }
}
