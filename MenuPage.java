import javax.swing.*;
import java.awt.*;



public class MenuPage {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton homeButton;
    private JButton incomeButton;
    private JButton expensesButton;
    private JButton goalButton;

    //public instance of the menu frame for calling it on the main file
    public JPanel menuPanel;


    public MenuPage() {
    //make the menu setup here

    
    menuPanel = new JPanel(); //menu panel instance
    menuPanel.setBackground(Color.decode("#2c2a2a"));
    menuPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(20, 20, 5, 20);

    //styling the buttons 
    
    homeButton = new JButton("Home");
    homeButton.setBackground(Color.decode("#FFFFFF"));
    homeButton.setForeground(Color.decode("#FF914D"));
    homeButton.setPreferredSize(new Dimension(100, 40));

    incomeButton = new JButton("Income");
    incomeButton.setBackground(Color.decode("#FFFFFF"));
    incomeButton.setForeground(Color.decode("#FF914D"));
    incomeButton.setPreferredSize(new Dimension(100, 40));

    expensesButton = new JButton("Expenses");
    expensesButton.setBackground(Color.decode("#FFFFFF"));
    expensesButton.setForeground(Color.decode("#FF914D"));
    expensesButton.setPreferredSize(new Dimension(100, 40));

    goalButton = new JButton("Goal");
    goalButton.setBackground(Color.decode("#FFFFFF"));
    goalButton.setForeground(Color.decode("#FF914D"));
    goalButton.setPreferredSize(new Dimension(100, 40));

    //styling buttons end ====

    gbc.gridy = 0;
    menuPanel.add(homeButton, gbc);
    gbc.gridy = 1;
    menuPanel.add(incomeButton, gbc);
    gbc.gridy = 2;
    menuPanel.add(expensesButton, gbc);
    gbc.gridy = 3;
    menuPanel.add(goalButton, gbc);

    }

    //card layout instance on menu
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
