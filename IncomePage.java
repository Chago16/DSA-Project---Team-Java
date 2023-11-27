import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

public class IncomePage {
    private static JLabel totalIncomeLabel = new JLabel();

    public static DefaultTableModel incModel = new DefaultTableModel(new Object[][]{}, new Object[]{"Amount", "Label"}) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public static JTable incTable = new JTable(incModel) {
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component comp = super.prepareRenderer(renderer, row, column);
            comp.setBackground(row % 2 == 0 ? Color.WHITE : super.getBackground());
            // Set the font for the entire row to bold
            Font boldFont = new Font("Poppins", Font.BOLD, 16);
            comp.setFont(boldFont);

            // Set the font for specific cells (e.g., columns 0 and 1) to plain
            if (column == 0 || column == 1) {
                Font plainFont = new Font("Poppins", Font.PLAIN, 16);
                comp.setFont(plainFont);
            }

            return comp;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    {
        // Set the font size for the table cells
        Font cellFont = new Font("Poppins", Font.PLAIN, 16);
        incTable.setFont(cellFont);

        // Set the font size for the column headers
        Font headerFont = new Font("Poppins", Font.BOLD, 20);
        JTableHeader header = incTable.getTableHeader();
        header.setFont(headerFont);
    }

    public JPanel incomePanel;

    public IncomePage() {

        incomePanel = new JPanel();
        incomePanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the Income Page");
        label.setFont(new Font("Poppins", Font.PLAIN, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        incomePanel.add(label, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(""));

        totalIncomeLabel = new JLabel("Total Income: 0.00");
        totalIncomeLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        tablePanel.add(totalIncomeLabel, BorderLayout.SOUTH);

        updateOnlyTotalIncome();

        Object[] columns = {"Amount", "Label"};
        incModel.setColumnIdentifiers(columns);
        incTable.setModel(incModel);
        incTable.setBackground(Color.LIGHT_GRAY);
        incTable.setForeground(Color.black);
        Font font = new Font("Poppins", Font.BOLD, 16);
        incTable.setFont(font);
        incTable.setRowHeight(30);

        JTextField textAmount = new JTextField();
        JTextField textLabel = new JTextField();
        Font textFieldFont = new Font("Poppins", Font.PLAIN, 18);
        textAmount.setFont(textFieldFont);
        textLabel.setFont(textFieldFont);

        JLabel labelAmount = new JLabel("Amount:");
        JLabel labelLabel = new JLabel("Label:");
        Font labelFont = new Font("Poppins", Font.BOLD, 18);
        labelAmount.setFont(labelFont);
        labelLabel.setFont(labelFont);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(Color.decode("#FF914D"));
        btnAdd.setBorder(new LineBorder(Color.decode("#FF914D")));
        Font buttonFont = new Font("Poppins", Font.BOLD, 18);
        btnAdd.setFont(buttonFont);

        JScrollPane pane = new JScrollPane(incTable);
        tablePanel.add(pane, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(incomePanel);
        incomePanel.setLayout(layout);
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
                .addComponent(totalIncomeLabel, GroupLayout.Alignment.TRAILING)
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
                .addComponent(totalIncomeLabel)
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
                    incModel.addRow(row);

                    toIncomeCSV(amountText, amountLabel, "IncomeTable.csv");

                    Variables.totalIncome += amountInt;
                    Variables funcVar = new Variables(); // para lang magamit functions
                    funcVar.updateToFile("Data.dat");

                    textAmount.setText("");
                    textLabel.setText("");

                    updateOnlyTotalIncome();

                    HomePage.updateAvailableBalance();
                } else {
                    showError("Please enter a valid numeric amount.");
                }
            }
        });

        // get selected row data From table to textfields
        incTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your logic here if needed
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
                .addComponent(totalIncomeLabel, GroupLayout.Alignment.TRAILING)
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
                .addComponent(totalIncomeLabel)
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
        Font errorMessageFont = new Font("Poppins", Font.PLAIN, 14); // Change "Poppins" and size as needed

        JLabel errorMessage = new JLabel(message);
        errorMessage.setFont(errorMessageFont);
        errorMessage.setForeground(Color.RED);
        errorMessage.setHorizontalAlignment(JLabel.CENTER);

        errorFrame.add(errorMessage);

        errorFrame.setVisible(true);
    }

    public static void updateOnlyTotalIncome() {

        Variables variablesFunc = new Variables();
        variablesFunc.updateFromFile("Data.dat");
        totalIncomeLabel.setText("Total Income: " + Variables.totalIncome);

    }

    public static void toIncomeCSV(String row0, String row1, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(row0 + "," + row1);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadIncCSVToTable(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2); // Split the line into two parts at the first comma encountered

                // Check if the line has valid content (non-empty)
                if (parts.length == 2) {
                    Object[] rowData = new Object[2];
                    rowData[0] = parts[0].trim();
                    rowData[1] = parts[1].trim();

                    incModel.addRow(rowData); // Add the row to the table model
                }
            }

            // Refresh the table view by resetting the table model
            ((DefaultTableModel) incTable.getModel()).fireTableDataChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromSavingsToInc(String amountString) {
        Object[] rowfromSavings = new Object[2];

        rowfromSavings[0] = amountString;
        rowfromSavings[1] = "From Savings";

        incModel.addRow(rowfromSavings);
        toIncomeCSV(amountString, "From Savings", "IncomeTable.csv");
    }
}
