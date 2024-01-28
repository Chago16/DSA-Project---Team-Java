import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


public class MenuPage {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton incomeButton;
    private JButton expensesButton;
    private JButton goalButton;
    private JButton tipsButton;
    private JButton aboutButton;

    // public instance of the menu frame for calling it on the main file
    public JPanel menuPanel;

    public MenuPage() {
        setUIFont(new FontUIResource(new Font("Poppins", Font.PLAIN, 12)));  // Set a default font for MenuPage

        menuPanel = new JPanel(); // menu panel instance
        menuPanel.setBackground(Color.decode("#2c2a2a"));
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 5, 20); 

        // Add image above buttons
        ImageIcon logoIcon = new ImageIcon("pictures/LogoMenuPage.png");
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH)));
        gbc.gridy = 0;
        menuPanel.add(logoLabel, gbc);

        // styling the buttons
        homeButton = createRoundedButton("Home", "pictures/home.png");
        incomeButton = createRoundedButton("Income", "pictures/income.png"); // Replace "income.png" with the actual icon file name
        expensesButton = createRoundedButton("Expenses", "pictures/expenses.png"); // Replace "expenses.png" with the actual icon file name
        goalButton = createRoundedButton("Savings", "pictures/savings.png"); // Replace "goal.png" with the actual icon file name
        tipsButton = createRoundedButton("Tips", "pictures/tips.png"); // Replace "expenses.png" with the actual icon file name
        aboutButton = createRoundedButton("About", "pictures/about.png"); // Replace "goal.png" with the actual icon file name

        // Add buttons to the menu panel
        gbc.gridy = 1; // Adjusted the starting position for buttons
        menuPanel.add(homeButton, gbc);
        gbc.gridy = 2;
        menuPanel.add(incomeButton, gbc);
        gbc.gridy = 3;
        menuPanel.add(expensesButton, gbc);
        gbc.gridy = 4;
        menuPanel.add(goalButton, gbc);
        gbc.gridy = 5;
        menuPanel.add(tipsButton, gbc);
        gbc.gridy = 6;
        menuPanel.add(aboutButton, gbc);
    }

    private JButton createRoundedButton(String buttonText, String iconName) {
        JButton button = new RoundButtonMenu(buttonText);
    
        // Load the icon from the file or resource
        try {
            ImageIcon icon = new ImageIcon(iconName);
            Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setBackground(Color.decode("#FFFFFF"));
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
        tipsButton.addActionListener(e -> cardLayout.show(mainPanel, "TipsPage"));
        aboutButton.addActionListener(e -> cardLayout.show(mainPanel, "AboutPage"));
        // Add action listeners for other buttons as needed
    }

// Set font for MenuPage components
    private static void setUIFont(FontUIResource font) {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keys = defaults.keys();
        while (keys.hasMoreElements()) {
        Object key = keys.nextElement();
        Object value = defaults.get(key);
        if (value instanceof FontUIResource) {
            defaults.put(key, font);
            }
        }
    }
}