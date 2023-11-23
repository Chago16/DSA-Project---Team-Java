import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
                    int choice = JOptionPane.showConfirmDialog(mframe, "Do you really want to exit?", "Exit",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (choice == JOptionPane.YES_OPTION) {
                        // Save any data or perform cleanup before exiting
                        System.exit(0);
                    }
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
}
