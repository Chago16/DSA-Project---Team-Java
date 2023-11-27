import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Show the splash screen
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.showSplashScreen(3000); // Display for 2000 milliseconds (2 seconds)

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

    private static void showExitDialog(JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Do you really want to exit?");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton yesButton = new JButton("Yes");
        yesButton.setBackground(Color.decode("#DDDDD0"));
        yesButton.setForeground(Color.BLACK);

        JButton noButton = new JButton("No");
        noButton.setBackground(Color.decode("#FF914D"));
        noButton.setForeground(Color.WHITE);

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
