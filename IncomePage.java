import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class IncomePage {

    private JLabel totalIncomeLabel = new JLabel();

    public JPanel incomePanel;

    public IncomePage() {

        incomePanel = new JPanel();
        incomePanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the Income Page");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        incomePanel.add(label, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(""));

        totalIncomeLabel = new JLabel("Total Income: 0.00");
        totalIncomeLabel.setFont(new Font("", Font.BOLD, 16));
        tablePanel.add(totalIncomeLabel, BorderLayout.SOUTH);

        updateOnlyTotalIncome();

        JTable table = new JTable();
        Object[] columns = {"Amount", "Label"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", Font.BOLD, 16);
        table.setFont(font);
        table.setRowHeight(30);

        JTextField textAmount = new JTextField();
        JTextField textLabel = new JTextField();

        JLabel labelAmount = new JLabel("Amount:");
        JLabel labelLabel = new JLabel("Label:");

        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(Color.decode("#FF914D"));
        btnAdd.setBorder(new LineBorder(Color.decode("#FF914D")));

        JScrollPane pane = new JScrollPane(table);
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

                if (isValidAmount(amountText)) {
                    int amountInt = Integer.parseInt(amountText);

                    row[0] = amountText;
                    row[1] = textLabel.getText();
                    model.addRow(row);

                    Variables.totalIncome += amountInt;
                    Variables funcVar = new Variables(); // para lang magamit functions
                    funcVar.updateToFile("Data.dat");

                    textAmount.setText("");
                    textLabel.setText("");

                    updateOnlyTotalIncome();
                } else {
                    showError("Please enter a valid numeric amount.");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {
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

        JLabel errorMessage = new JLabel(message);
        errorMessage.setForeground(Color.RED);
        errorMessage.setHorizontalAlignment(JLabel.CENTER);

        errorFrame.add(errorMessage);

        errorFrame.setVisible(true);
    }

    public void updateOnlyTotalIncome() {
    
        Variables variablesFunc = new Variables();
        variablesFunc.updateFromFile("Data.dat");
        totalIncomeLabel.setText("Total Income: " + Variables.totalIncome);

    }

}

    
