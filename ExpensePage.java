import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;


public class ExpensePage {

    private static JLabel totalExpenseLabel = new JLabel();

    public static DefaultTableModel expModel = new DefaultTableModel(new Object[][] {}, new Object[] { "Amount", "Label", "Date" }) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public static JTable expTable = new JTable(expModel) {
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component comp = super.prepareRenderer(renderer, row, column);
            comp.setBackground(row % 2 == 0 ? Color.WHITE : super.getBackground());
            return comp;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    {
        // Set the font size for the table cells
        Font cellFont = new Font("Poppins", Font.PLAIN, 16); // Change "Poppins" as needed
        expTable.setFont(cellFont);

        // Set the font size for the column headers
        Font headerFont = new Font("Poppins", Font.BOLD, 20);
        JTableHeader header = expTable.getTableHeader();
        header.setBackground(Color.decode("#DDDDD0"));  // Set the background color of the header
        header.setFont(headerFont);
    }

    public JPanel expensePanel;
    public JDateChooser dateChooser;
    public ExpensePage() {

        expensePanel = new JPanel();
        expensePanel.setLayout(new BorderLayout());
        expensePanel.setBackground(Color.decode("#FFFFFF"));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(""));

        totalExpenseLabel = new JLabel("Total Expense: 0.00");
        totalExpenseLabel.setFont(new Font("Poppins", Font.BOLD, 20)); // Change "Poppins" as needed
        tablePanel.add(totalExpenseLabel, BorderLayout.SOUTH);

        updateOnlyTotalExpense();

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Poppins", Font.PLAIN, 20)); // Set font as needed
        dateChooser.setDateFormatString("MM/dd/yyyy"); // Set date format
        dateChooser.setBorder(BorderFactory.createTitledBorder("Date"));
        dateChooser.setPreferredSize(new Dimension(200, 60));

        // Initialize the table model only once
        expModel = new DefaultTableModel(new Object[][] {}, new Object[] { "Amount", "Label", "Date" }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    loadExpCSVToTable("ExpensesTable.csv");
        
        
        Object[] columns = {"Amount", "Label", "Date"};
        expModel.setColumnIdentifiers(columns);
        expTable.setModel(expModel);
        expTable.setBackground(Color.LIGHT_GRAY);
        expTable.setForeground(Color.black);
        Font font = new Font("Poppins", Font.PLAIN, 16); // Change "Poppins" as needed
        expTable.setFont(font);
        expTable.setRowHeight(30);

        JTextField textAmount = new RoundedTextField(); // Use RoundedTextField instead of JTextField
        JTextField textLabel = new RoundedTextField();
        Font textFieldFont = new Font("Poppins", Font.PLAIN, 20); // Change "Poppins" as needed
        textAmount.setFont(textFieldFont);
        textLabel.setFont(textFieldFont);

        JLabel labelAmount = new JLabel("Amount:");
        JLabel labelLabel = new JLabel("Label:");
        Font labelFont = new Font("Poppins", Font.BOLD, 18); // Change "Poppins" as needed
        labelAmount.setFont(labelFont);
        labelLabel.setFont(labelFont);

        JButton btnAdd = new RoundedOrangeButton("Add");
        btnAdd.setBackground(Color.decode("#FF914D"));
        btnAdd.setBorder(new LineBorder(Color.decode("#FF914D")));
        Font buttonFont = new Font("Poppins", Font.BOLD, 18); // Change "Poppins" as needed
        btnAdd.setFont(buttonFont);

        JScrollPane pane = new JScrollPane(expTable);
        pane.setBackground(Color.WHITE);  // Set the background color of the JScrollPane
        pane.getViewport().setBackground(Color.WHITE);  // Set the background color of the viewport
        pane.setPreferredSize(new Dimension(400, 200));
        tablePanel.add(pane, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(expensePanel);
        expensePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Add dateChooser to the horizontal and vertical groups
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(tablePanel)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelAmount)
                        .addComponent(labelLabel)
                        .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)) // Add dateChooser here
                .addGroup(layout.createParallelGroup()
                        .addComponent(textAmount, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
        .addComponent(totalExpenseLabel, GroupLayout.Alignment.TRAILING)
    );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(tablePanel)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelAmount)
                .addComponent(textAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelLabel)
                .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) // Add dateChooser here
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnAdd))
            .addComponent(totalExpenseLabel)
    );
    Object[] row = new Object[3];
    
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = textAmount.getText();
                String amountLabel = textLabel.getText();
        
                // Set the current date
                Date selectedDate = dateChooser.getDate(); // Use the selected date from JDateChooser
        
                // Check if label is empty
                if (amountLabel.isEmpty()) {
                    showError("Please enter a label.");
                    return; // Exit the method if label is empty
                }
        
                // Check if date is empty
                if (selectedDate == null) {
                    showError("Please enter a date.");
                    return; // Exit the method if date is empty
                }
        
                if (isValidAmount(amountText)) {
                    int amountInt = Integer.parseInt(amountText);
        
                    // Check if the amount is greater than the available budget
                    if (amountInt > Variables.pocketMoney) {
                        showError("Oops! Your available budget is insufficient for this expense. Please adjust the amount.");
                        textAmount.setText("");
                        textLabel.setText("");
                        return; // Exit the method if the amount is not valid
                    }
        
                    Object[] row = new Object[3];
                    row[0] = amountText;
                    row[1] = amountLabel;
                    row[2] = new SimpleDateFormat("MM/dd/yyyy").format(selectedDate);
                    expModel.addRow(row);
        
                    // Include the date in your data storage method, e.g., toExpenseCSV
                    toExpenseCSV(amountText, amountLabel, selectedDate, "ExpensesTable.csv");
        
                    Variables.totalExpenses += amountInt;
                    Variables.pocketMoney -= amountInt; // Deduct the amount from the available budget
        
                    Variables funcVar = new Variables();
                    funcVar.updateToFile("Data.dat");
        
                    textAmount.setText("");
                    textLabel.setText("");
        
                    // Clear the JDateChooser text field
                    dateChooser.setDate(null);
        
                    updateOnlyTotalExpense();
        
                    HomePage.updateAvailableBalance();
                } else {
                    showError("Please enter a valid numeric amount.");
                    textAmount.setText("");
                    textLabel.setText("");
                }
            }
        });
        

        // Add dateChooser to the horizontal and vertical groups
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(tablePanel)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(labelAmount)
                        .addComponent(labelLabel)
                        .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)) // Add dateChooser here
                .addGroup(layout.createParallelGroup()
                        .addComponent(textAmount, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
            .addComponent(totalExpenseLabel, GroupLayout.Alignment.TRAILING)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(tablePanel)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelAmount)
                .addComponent(textAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelLabel)
                .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnAdd))
            .addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) // Add dateChooser here
            .addComponent(totalExpenseLabel)
        );
    }

    private static boolean isValidAmount(String amountText) {
        try {
            Double.parseDouble(amountText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void showError(String message) {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setSize(300, 100);
        errorFrame.setLocationRelativeTo(null);
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        Font errorMessageFont = new Font("Poppins", Font.PLAIN, 14);
    
        JLabel errorMessage = new JLabel(message);
        errorMessage.setFont(errorMessageFont);
        errorMessage.setForeground(Color.RED);
        errorMessage.setHorizontalAlignment(JLabel.CENTER); // Center-align the text
    
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(errorMessage, BorderLayout.CENTER);
        errorFrame.add(panel);
    
        errorFrame.setVisible(true);
    }
    
    

    public static void updateOnlyTotalExpense() {

        Variables variablesFunc = new Variables();
        variablesFunc.updateFromFile("Data.dat");
        totalExpenseLabel.setText("Total Expense: " + Variables.totalExpenses);

    }

    public static void toExpenseCSV(String row0, String row1, Date selectedDate, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(selectedDate);
            writer.write(row0 + "," + row1 + "," + formattedDate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void loadExpCSVToTable(String fileName) {
        // Clear the existing data in the table model
        expModel.setRowCount(0);
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
    
                if (parts.length == 3) {
                    Object[] rowData = new Object[3];
                    rowData[0] = parts[0].trim();
                    rowData[1] = parts[1].trim();
                    rowData[2] = parts[2].trim();
    
                    expModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            // Print or log the error
            e.printStackTrace();
        }
    }

    public static void fromSavingsToExp(String amountString) {
        Object[] rowfromSavings = new Object[2];

        rowfromSavings[0] = amountString;
        rowfromSavings[1] = "To Savings";

        expModel.addRow(rowfromSavings);
        // Assuming amountString is the amount and dateChooser.getDate() returns the selected date
        toExpenseCSV(amountString, "To Savings", new Date(), "ExpensesTable.csv");
    }
}