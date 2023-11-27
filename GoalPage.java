import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class GoalPage {

    // public instance of the main frame for calling it on the main file
    public JPanel goalPanel;

    public static JLabel totalSavingsField;

    public GoalPage() {
        // make the goal panel here
        goalPanel = new JPanel();
        goalPanel.setLayout(new BorderLayout()); // Set layout manager to BorderLayout
        JPanel goalContentPanel = new JPanel(new GridBagLayout());
        goalContentPanel.setBackground(Color.WHITE); // Changing background color to white

        // Creating a larger rectangular panel inside the goal content panel
        JPanel totalSavingsPanel = new JPanel();
        totalSavingsPanel.setBackground(Color.decode("#FF914D")); // Setting background color of the larger panel
        int width = 800;
        int height = 250;
        totalSavingsPanel.setPreferredSize(new java.awt.Dimension(width, height));

        // Adding the larger panel to the goal content panel using GridBagConstraints to center it
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        goalContentPanel.add(totalSavingsPanel, gbc);

        // Creating "Total Savings" label

        // Creating editable text field for total savings
        totalSavingsField = new JLabel();
        totalSavingsField.setFont(new Font("Poppins", Font.BOLD, 40)); // Change font to Poppins
        totalSavingsField.setBackground(totalSavingsPanel.getBackground());
        totalSavingsField.setForeground(Color.WHITE); // Set text color to white
        totalSavingsField.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Set layout for totalSavingsPanel
        totalSavingsPanel.setLayout(new BorderLayout());
        totalSavingsPanel.add(totalSavingsField, BorderLayout.CENTER);

        // Creating "Withdraw" button
        JButton withdrawButton = createRoundedButton("Withdraw");
        withdrawButton.setFont(new Font("Poppins", Font.PLAIN, 24)); // Change font to Poppins
        withdrawButton.setBackground(Color.decode("#FFFFFF"));
        withdrawButton.addActionListener(e -> showWithdrawDialog());

        // Creating "Deposit" button
        JButton depositButton = createRoundedButton("Deposit");
        depositButton.setFont(new Font("Poppins", Font.PLAIN, 24)); // Change font to Poppins
        depositButton.setBackground(Color.decode("#FFFFFF"));
        depositButton.addActionListener(e -> showDepositDialog());

        // Creating panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);

        // Adding the button panel to the totalSavingsPanel
        totalSavingsPanel.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.setBackground(Color.decode("#DDDDD0"));

        // Adding the goal content panel to the center of goalPanel
        goalPanel.add(goalContentPanel, BorderLayout.CENTER);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new RoundedButtonGoal(text);
        button.setFocusPainted(false);
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

