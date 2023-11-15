import javax.swing.*;
import javax.swing.border.LineBorder;
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
    menuPanel.setBounds(0, 0, 250, 600);
    menuPanel.setBackground(Color.decode("#2c2a2a"));

    //styling the buttons 

    homeButton = new JButton("Home");
    homeButton.setBounds(50, 100, 150, 50);
    homeButton.setBackground(Color.decode("#FFFFFF"));
    homeButton.setBorder(new LineBorder(Color.decode("#FFFFFF")));
    homeButton.setForeground(Color.decode("#FF914D"));

    incomeButton = new JButton("Income");
    incomeButton.setBounds(50, 200, 150, 50);
    incomeButton.setBackground(Color.decode("#FFFFFF"));
    incomeButton.setBorder(new LineBorder(Color.decode("#FFFFFF")));
    incomeButton.setForeground(Color.decode("#FF914D"));

    expensesButton = new JButton("Expenses");
    expensesButton.setBounds(50, 300, 150, 50);
    expensesButton.setBackground(Color.decode("#FFFFFF"));
    expensesButton.setBorder(new LineBorder(Color.decode("#FFFFFF")));
    expensesButton.setForeground(Color.decode("#FF914D"));

    goalButton = new JButton("Goal");
    goalButton.setBounds(50, 400, 150, 50);
    goalButton.setBackground(Color.decode("#FFFFFF"));
    goalButton.setBorder(new LineBorder(Color.decode("#FFFFFF")));
    goalButton.setForeground(Color.decode("#FF914D"));

    //styling buttons end ====

    menuPanel.add(homeButton);
    menuPanel.add(incomeButton);
    menuPanel.add(expensesButton);
    menuPanel.add(goalButton);

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
