import javax.swing.*;
import java.awt.*;


public class Main {

public static void main(String[] args) {

    //creates a card layout for the mainPanel only start
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout); //instance of main panel

    

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
    mp.setCardLayout(cardLayout, mainPanel); //experimental
    mp.addListeners();


    // menu panel instance end =====


    //creates the frame for the whole GUI
    JFrame mframe = new JFrame("Coffer Bliss");   
    mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mframe.setSize(1000, 600);
    mframe.setLocationRelativeTo(null);
    mframe.add(mainPanel);
    
    

    mframe.add(mainPanel, BorderLayout.CENTER);
    mframe.add(menuPanel, BorderLayout.WEST);
    mframe.setVisible(true);
    
    //whole gui end =====

}
}