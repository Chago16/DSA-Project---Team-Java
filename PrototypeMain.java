import javax.swing.*;
import java.awt.*;

public class PrototypeMain {
    public static void main(String[] args) {
        // Create the main window
        JFrame frame = new JFrame("Fixed Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Set up a CardLayout for easy screen switching
        CardLayout cardLayout = new CardLayout();

        // Create a main panel to hold different screens
        JPanel mainPanel = new JPanel(cardLayout);

        // Create panels for different screens (Home, Expenses, Budget)
        JPanel homePanel = new JPanel();
        JPanel expensesPanel = new JPanel();
        JPanel budgetPanel = new JPanel();

        // Design the content for each screen
        JLabel homeLabel = new JLabel("Welcome to our expenses calculator app");
        homePanel.add(homeLabel);

        JLabel expensesLabel = new JLabel("Input your expenses here for the week");
        JTextField expensesInput = new JTextField(20);
        expensesPanel.add(expensesLabel);
        expensesPanel.add(expensesInput);

        JLabel budgetLabel = new JLabel("Wow, you saved 9999 pesos this week");
        budgetPanel.add(budgetLabel);

        // Add screens to the main panel
        mainPanel.add(homePanel, "Home");
        mainPanel.add(expensesPanel, "Expenses");
        mainPanel.add(budgetPanel, "Budget");

        // Create menu buttons for navigation
        JButton homeButton = new JButton("Home");
        JButton expensesButton = new JButton("Expenses");
        JButton budgetButton = new JButton("Budget");

        // Attach action listeners to buttons for screen switching
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        expensesButton.addActionListener(e -> cardLayout.show(mainPanel, "Expenses"));
        budgetButton.addActionListener(e -> cardLayout.show(mainPanel, "Budget"));

        // Create a menu panel to hold the menu buttons
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuPanel.add(homeButton);
        menuPanel.add(expensesButton);
        menuPanel.add(budgetButton);

        // Add the main panel and menu panel to the main window
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.SOUTH);

        // Set frame properties and make it visible
        frame.setVisible(true);
    }
}