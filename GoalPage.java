import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        totalSavingsField.setFont(new Font("Arial", Font.BOLD, 40));
        totalSavingsField.setBackground(totalSavingsPanel.getBackground());
        totalSavingsField.setForeground(Color.WHITE); // Set text color to white
        totalSavingsField.setBorder(BorderFactory.createEmptyBorder(0, 100, 50, 100));

        // Set layout for totalSavingsPanel
        totalSavingsPanel.setLayout(new BorderLayout());
        totalSavingsPanel.add(totalSavingsField, BorderLayout.CENTER);

        // Creating "Withdraw" button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 24));
        withdrawButton.setBackground(Color.decode("#FFFFFF"));
        withdrawButton.addActionListener(e -> showWithdrawDialog());

        // Creating "Deposit" button
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 24));
        depositButton.setBackground(Color.decode("#FFFFFF"));
        depositButton.addActionListener(e -> showDepositDialog());

        // Creating panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);

        // Adding the button panel to the totalSavingsPanel
        totalSavingsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adding the goal content panel to the center of goalPanel
        goalPanel.add(goalContentPanel, BorderLayout.CENTER);
    }

    private void showWithdrawDialog() {
        JTextField userInputField = new JTextField(10);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter the amount to withdraw:"), BorderLayout.NORTH);
        panel.add(userInputField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(null, panel, "Withdraw Transaction",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            handleTransaction(userInputField.getText(), "Withdraw");
        }
    }

    private void showDepositDialog() {
        JTextField userInputField = new JTextField(10);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Enter the amount to deposit:"), BorderLayout.NORTH);
        panel.add(userInputField, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(null, panel, "Deposit Transaction",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            handleTransaction(userInputField.getText(), "Deposit");
        }
    }

    private void handleTransaction(String amountString, String transactionType) {
        try {
            double amount = Double.parseDouble(amountString);
            if (transactionType.equals("Withdraw")) {

                Variables.totalIncome += amount;
                Variables.savings -= amount;
                Variables.pocketMoney += amount;
                Variables varUse = new Variables();
                varUse.updateToFile("Data.dat");

                updateSavings();

                IncomePage.updateOnlyTotalIncome();
                HomePage.updateAvailableBalance();
                IncomePage.fromSavingsToInc(amountString);

                
            } else if (transactionType.equals("Deposit")) {

                Variables.totalExpenses += amount;
                Variables.savings += amount;
                Variables.pocketMoney -= amount;
                Variables varUse = new Variables();
                varUse.updateToFile("Data.dat");

                updateSavings();
                
                ExpensePage.updateOnlyTotalExpense();
                HomePage.updateAvailableBalance();
                ExpensePage.fromSavingsToExp(amountString);

            }
            // Update the totalSavingsField text accordingly
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    public static void updateSavings(){

        Variables varUse = new Variables();
        varUse.updateFromFile("Data.dat");
        totalSavingsField.setText("Total Savings: " + Variables.savings);

    }
}

