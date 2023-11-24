import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPage {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton incomeButton;
    private JButton expensesButton;
    private JButton goalButton;

    // public instance of the menu frame for calling it on the main file
    public JPanel menuPanel;

    public MenuPage() {
        // make the menu setup here
        menuPanel = new JPanel(); // menu panel instance
        menuPanel.setBackground(Color.decode("#2c2a2a"));
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 5, 20); // Adjusted left margin

        // Add image above buttons
        ImageIcon logoIcon = new ImageIcon("LogoMenuPage.png");
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        gbc.gridy = 0;
        menuPanel.add(logoLabel, gbc);


        // styling the buttons
        homeButton = createButton("Home", "home.png");
        incomeButton = createButton("Income", "income.png"); // Replace "income.png" with the actual icon file name
        expensesButton = createButton("Expenses", "expenses.png"); // Replace "expenses.png" with the actual icon file name
        goalButton = createButton("Goal", "goal.png"); // Replace "goal.png" with the actual icon file name

        // Add buttons to the menu panel
        gbc.gridy = 2;
        menuPanel.add(homeButton, gbc);
        gbc.gridy = 3;
        menuPanel.add(incomeButton, gbc);
        gbc.gridy = 4;
        menuPanel.add(expensesButton, gbc);
        gbc.gridy = 5;
        menuPanel.add(goalButton, gbc);
    }

    private JButton createButton(String buttonText, String iconName) {
        JButton button = new JButton(buttonText);

        // Load the icon from the file or resource
        try {
            ImageIcon icon = new ImageIcon(iconName);
            Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setBackground(Color.decode("#FFFFFF"));
        button.setForeground(Color.decode("#FF914D"));
        button.setPreferredSize(new Dimension(150, 40)); // Adjust the width value here

        return button;
    }

    // card layout instance on menu
    public void setCardLayout(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        addListeners(); // Add action listeners for buttons after setting the card layout
    }

    // Adding action listeners for buttons to control the card layout
    public void addListeners() {
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "HomePage"));
        incomeButton.addActionListener(e -> cardLayout.show(mainPanel, "IncomePage"));
        expensesButton.addActionListener(e -> cardLayout.show(mainPanel, "ExpensePage"));
        goalButton.addActionListener(e -> cardLayout.show(mainPanel, "GoalPage"));
        // Add action listeners for other buttons as needed
    }
}
