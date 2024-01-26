import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;



public class GoalPage {

    // public instance of the main frame for calling it on the main file
    public JPanel goalPanel;

    public static JLabel totalSavingsField;

    private static JLabel savingsGoalLabel;
    private static JProgressBar savingsProgressBar;
    private static double savingsGoal = 0.0;

    public GoalPage() {
        // make the goal panel here
        goalPanel = new JPanel(new BorderLayout());

        // Creating a larger rectangular panel inside the goal content panel
        JPanel totalSavingsPanel = new JPanel(new BorderLayout());
        totalSavingsPanel.setBackground(Color.decode("#FFFFFF")); // Setting background color

        // Creating "Total Savings" label
        totalSavingsField = new JLabel("Total Savings: " + savingsGoal);
        totalSavingsField.setFont(new Font("Poppins", Font.BOLD, 40));
        totalSavingsField.setForeground(Color.decode("#000000"));
        totalSavingsField.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        totalSavingsPanel.add(totalSavingsField, BorderLayout.CENTER);

        // Creating "Withdraw" button
        JButton withdrawButton = createRoundedButton("Withdraw");
        JButton depositButton = createRoundedButton("Deposit");
        JButton setGoalButton = createRoundedButton("Set Goal");

        // Creating panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(setGoalButton);
        buttonPanel.setBackground(Color.decode("#DDDDD0"));

        // Adding the button panel to the totalSavingsPanel
        totalSavingsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adding the totalSavingsPanel to the goalPanel
        goalPanel.add(totalSavingsPanel, BorderLayout.CENTER);

        // Creating a panel for savings goal
        JPanel savingsGoalPanel = new JPanel(new BorderLayout());
        savingsGoalPanel.setBackground(Color.decode("#FF914D"));

        // Creating label for savings goal
        savingsGoalLabel = new JLabel("Savings Goal: $" + savingsGoal);
        savingsGoalLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        savingsGoalLabel.setForeground(Color.WHITE);
        savingsGoalLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Creating progress bar for savings goal
        savingsProgressBar = new JProgressBar(0, (int) savingsGoal);
        savingsProgressBar.setStringPainted(true);

        // Adding components to the savings goal panel
        savingsGoalPanel.add(savingsGoalLabel, BorderLayout.NORTH);
        savingsGoalPanel.add(savingsProgressBar, BorderLayout.CENTER);

        // Adding the savings goal panel to the goalPanel
        goalPanel.add(savingsGoalPanel, BorderLayout.SOUTH);

        // Add action listeners
        withdrawButton.addActionListener(e -> showWithdrawDialog());
        depositButton.addActionListener(e -> showDepositDialog());
        setGoalButton.addActionListener(e -> showSetGoalDialog());
        
        // Load the savings goal when the application starts
        loadSavingsGoal();
    }

    private JButton createRoundedButton(String text) {
        JButton button = new RoundedButtonGoal(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Poppins", Font.PLAIN, 24));
        button.setBackground(Color.decode("#FFFFFF"));
        return button;
    }

    private void showWithdrawDialog() {
        JTextField userInputField = new JTextField(10);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter the amount to withdraw:"), BorderLayout.NORTH);
        panel.add(userInputField, BorderLayout.CENTER);
    
        // Set font for the dialog
        setUIFont(new FontUIResource(new Font("Poppins", Font.PLAIN, 14))); // Change font to Poppins
    
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
    
        okButton.setBackground(Color.WHITE); // Set background color to white
        cancelButton.setBackground(Color.WHITE); // Set background color to white
    
        // Add action listeners to the buttons
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle OK button action
                JOptionPane.getRootFrame().dispose();
                handleTransaction(userInputField.getText(), "Withdraw");
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Cancel button action
                JOptionPane.getRootFrame().dispose();
            }
        });
    
        // Create a custom panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    
        // Add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        int result = JOptionPane.showOptionDialog(null, panel, "Withdraw Transaction",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{},
                JOptionPane.UNINITIALIZED_VALUE);
    
        if (result == JOptionPane.OK_OPTION) {
            handleTransaction(userInputField.getText(), "Withdraw");
        }
    }
    

    private void showDepositDialog() {
        JTextField userInputField = new JTextField(10);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter the amount to deposit:"), BorderLayout.NORTH);
        panel.add(userInputField, BorderLayout.CENTER);

        // Set font for the dialog
        setUIFont(new FontUIResource(new Font("Poppins", Font.PLAIN, 14))); // Change font to Poppins

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.setBackground(Color.WHITE); // Set background color to white
        cancelButton.setBackground(Color.WHITE); // Set background color to white

        // Add action listeners to the buttons
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle OK button action
                JOptionPane.getRootFrame().dispose();
                handleTransaction(userInputField.getText(), "Deposit");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Cancel button action
                JOptionPane.getRootFrame().dispose();
            }
        });

        // Create a custom panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        int result = JOptionPane.showOptionDialog(null, panel, "Deposit Transaction",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

        // Handle the result if needed
        if (result == JOptionPane.OK_OPTION) {
            handleTransaction(userInputField.getText(), "Deposit");
        }
    }
    
    private void showSetGoalDialog() {
        JTextField goalInputField = new JTextField(10);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter the savings goal:"), BorderLayout.NORTH);
        panel.add(goalInputField, BorderLayout.CENTER);

        // Set font for the dialog
        setUIFont(new FontUIResource(new Font("Poppins", Font.PLAIN, 14))); // Change font to Poppins

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.setBackground(Color.WHITE);
        cancelButton.setBackground(Color.WHITE);

        okButton.addActionListener(e -> {
            setSavingsGoal(goalInputField.getText());
            JOptionPane.getRootFrame().dispose();
        });

        cancelButton.addActionListener(e -> JOptionPane.getRootFrame().dispose());

        // Create a custom panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JOptionPane.showOptionDialog(null, panel, "Set Savings Goal",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{},
                JOptionPane.UNINITIALIZED_VALUE);
    }

    private void setSavingsGoal(String goalString) {
        try {
            double newGoal = Double.parseDouble(goalString);
            if (newGoal >= 0) {
                savingsGoal = newGoal;
                updateProgressBar();
                updateSavingsGoalLabel();
                
                // Save the updated goal to a file
                saveGoalToFile();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid goal amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid goal amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveGoalToFile() {
        try (PrintWriter writer = new PrintWriter("savings_goal.txt")) {
            writer.println(savingsGoal);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }
    private void loadSavingsGoal() {
        File file = new File("savings_goal.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                if (scanner.hasNextDouble()) {
                    savingsGoal = scanner.nextDouble();
                    updateProgressBar();
                    updateSavingsGoalLabel();
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
            }
        }
    }
    
    private static void updateProgressBar() {
        double difference = Variables.savings - savingsGoal;
    
        if (difference >= 0) {
            // If savings are equal or exceed the goal, set the progress to the maximum
            savingsProgressBar.setMaximum((int) savingsGoal);
            savingsProgressBar.setValue((int) savingsGoal);
        } else {
            // If savings are less than the goal, set the progress to the savings amount
            savingsProgressBar.setMaximum((int) savingsGoal);
            savingsProgressBar.setValue((int) Variables.savings);
        }
    }

    private static void updateSavingsGoalLabel() {
        savingsGoalLabel.setText("Savings Goal: " + savingsGoal);
    }


    private void handleTransaction(String amountString, String transactionType) {
        try {
            double amount = Double.parseDouble(amountString);
            if (transactionType.equals("Withdraw")) {

                if((Variables.savings - amount) >= 0){

                Variables.totalIncome += amount;
                Variables.savings -= amount;
                Variables.pocketMoney += amount;
                Variables varUse = new Variables();
                varUse.updateToFile("Data.dat");

                updateSavings();

                IncomePage.updateOnlyTotalIncome();
                HomePage.updateAvailableBalance();
                IncomePage.fromSavingsToInc(amountString);
                
            } else {
                // Create a custom panel for the warning message
                JPanel warningPanel = new JPanel(new BorderLayout());
                JLabel warningMessage = new JLabel("Insufficient amount");
                warningMessage.setFont(new Font("Poppins", Font.PLAIN, 14)); // Set font for the warning message
                warningMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                warningMessage.setForeground(Color.RED); // Set text color to red
            
                // Set background color of the warning panel to white
                warningPanel.setBackground(Color.WHITE);
            
                // Add the warning message label to the warning panel
                warningPanel.add(warningMessage, BorderLayout.CENTER);
            
                // Show the warning dialog
                JOptionPane.showOptionDialog(null, warningPanel, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{}, null);
            }

                
            } else if (transactionType.equals("Deposit")) {

                if((Variables.pocketMoney - amount) >= 0){

                Variables.totalExpenses += amount;
                Variables.savings += amount;
                Variables.pocketMoney -= amount;
                Variables varUse = new Variables();
                varUse.updateToFile("Data.dat");

                updateSavings();
                
                ExpensePage.updateOnlyTotalExpense();
                HomePage.updateAvailableBalance();
                ExpensePage.fromSavingsToExp(amountString);

                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient amount", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }

            checkGoalAchievement();
            // Update the totalSavingsField text accordingly
        } catch (NumberFormatException ex) {
            // Create a custom panel for the error message
            JPanel errorPanel = new JPanel(new BorderLayout());
            JLabel errorMessage = new JLabel("Invalid amount. Please enter a valid number.");
            errorMessage.setFont(new Font("Poppins", Font.PLAIN, 14)); // Set font for the error message
            errorMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            errorMessage.setForeground(Color.RED); // Set text color to red
        
            // Set background color of the error panel to white
            errorPanel.setBackground(Color.WHITE);
        
            // Add the error message label to the error panel
            errorPanel.add(errorMessage, BorderLayout.CENTER);
        
            // Show the error dialog
            JOptionPane.showOptionDialog(null, errorPanel, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[]{}, null);
        }
    }

    public static void updateSavings(){

        Variables varUse = new Variables();
        varUse.updateFromFile("Data.dat");
        totalSavingsField.setText("Total Savings: " + Variables.savings);

    }

    private void checkGoalAchievement() {
        if (Variables.savings >= savingsGoal) {
            JOptionPane.showMessageDialog(null, "Congratulations! You have achieved your savings goal!", "Goal Achieved", JOptionPane.INFORMATION_MESSAGE);
            savingsGoal = 0.0;
            updateProgressBar();
            updateSavingsGoalLabel();
        }
    }
    // Method to set the font for the dialog
    private static void setUIFont(FontUIResource font) {
        Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }
    }
}
