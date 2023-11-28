import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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

public class ExpensePage {
    private static JLabel totalExpenseLabel = new JLabel();

    public static DefaultTableModel expModel = new DefaultTableModel(new Object[][] {}, new Object[] { "Amount", "Label" }) {
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
    public ExpensePage() {

        expensePanel = new JPanel();
        expensePanel.setLayout(new BorderLayout());
        
        JPanel imageLabelPanel = new JPanel();
        imageLabelPanel.setLayout(new BorderLayout());
        
        JLabel label = new JLabel("Welcome to the Expenses Page");
        label.setFont(new Font("Poppins", Font.PLAIN, 30));
        ImageIcon imageIcon = createImageIcon("pictures/expenses.png");
        label.setIcon(imageIcon);

        expensePanel.add(imageLabelPanel, BorderLayout.NORTH);
        expensePanel.add(label, BorderLayout.NORTH);
        expensePanel.setBackground(Color.decode("#FFFFFF"));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(""));

        totalExpenseLabel = new JLabel("Total Expense: 0.00");
        totalExpenseLabel.setFont(new Font("Poppins", Font.BOLD, 20)); // Change "Poppins" as needed
        tablePanel.add(totalExpenseLabel, BorderLayout.SOUTH);

        updateOnlyTotalExpense();

        Object[] columns = {"Amount", "Label"};
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
        tablePanel.add(pane, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(expensePanel);
        expensePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(tablePanel)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(labelAmount)
                                .addComponent(labelLabel))
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
                .addComponent(totalExpenseLabel)
        );

        Object[] row = new Object[2];

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = textAmount.getText();
                String amountLabel = textLabel.getText();

                if (isValidAmount(amountText)) {
                    int amountInt = Integer.parseInt(amountText);

                    row[0] = amountText;
                    row[1] = amountLabel;
                    expModel.addRow(row);

                    toExpenseCSV(amountText, amountLabel, "ExpensesTable.csv");

                    Variables.totalExpenses += amountInt;
                    Variables funcVar = new Variables(); // para lang magamit functions
                    funcVar.updateToFile("Data.dat");

                    textAmount.setText("");
                    textLabel.setText("");

                    updateOnlyTotalExpense();

                    HomePage.updateAvailableBalance();
                } else {
                    showError("Please enter a valid numeric amount.");
                    textAmount.setText("");
                    textLabel.setText("");
                }
            }
        });

        // Explicitly set the horizontal and vertical groups
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(label)  // Add the label to the horizontal group
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(labelAmount)
                                .addComponent(labelLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(textAmount, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                .addComponent(tablePanel)
                .addComponent(totalExpenseLabel, GroupLayout.Alignment.TRAILING)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(label)  // Add the label to the vertical group
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAmount)
                        .addComponent(textAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelLabel)
                        .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd))
                .addComponent(tablePanel)
                .addComponent(totalExpenseLabel)
        );
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            Image img = new ImageIcon(imgURL).getImage();
            img = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
        errorMessage.setHorizontalAlignment(JLabel.CENTER);
    
        errorFrame.add(errorMessage);
    
        errorFrame.setVisible(true);
    }
    

    public static void updateOnlyTotalExpense() {

        Variables variablesFunc = new Variables();
        variablesFunc.updateFromFile("Data.dat");
        totalExpenseLabel.setText("Total Expense: " + Variables.totalExpenses);

    }

    public static void toExpenseCSV(String row0, String row1, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(row0 + "," + row1);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadExpCSVToTable(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2); // Split the line into two parts at the first comma encountered

                // Check if the line has valid content (non-empty)
                if (parts.length == 2) {
                    Object[] rowData = new Object[2];
                    rowData[0] = parts[0].trim();
                    rowData[1] = parts[1].trim();

                    expModel.addRow(rowData); // Add the row to the table model
                }
            }

            // Refresh the table view by resetting the table model
            ((DefaultTableModel) expTable.getModel()).fireTableDataChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromSavingsToExp(String amountString) {
        Object[] rowfromSavings = new Object[2];

        rowfromSavings[0] = amountString;
        rowfromSavings[1] = "To Savings";

        expModel.addRow(rowfromSavings);
        toExpenseCSV(amountString, "To Savings", "ExpensesTable.csv");

    }
}