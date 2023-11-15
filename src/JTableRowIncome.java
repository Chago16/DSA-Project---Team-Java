import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class JTableRowIncome {

    public static void setupTable(JPanel panel) {

        JTable table = new JTable();

        Object[] columns = { "Amount", "Label" };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JFormattedTextField(new DecimalFormat("#.00"))));

        JTextField textAmount = new JTextField();
        textAmount.setBounds(20, 330, 200, 30);
        JTextField textLabel = new JTextField();
        textLabel.setBounds(20, 390, 200, 30);

        JLabel labelAmount = new JLabel("Amount:");
        labelAmount.setBounds(20, 300, 200, 30);
        JLabel labelLabel = new JLabel("Label:");
        labelLabel.setBounds(20, 360, 200, 30);

        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");

        btnAdd.setBounds(300, 320, 200, 30);
        btnUpdate.setBounds(300, 365, 200, 30);
        btnDelete.setBounds(300, 410, 200, 30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 750, 300);

        panel.setLayout(null);

        panel.add(pane);

        panel.add(labelAmount);
        panel.add(labelLabel);

        panel.add(textAmount);
        panel.add(textLabel);

        panel.add(btnAdd);
        panel.add(btnDelete);
        panel.add(btnUpdate);

        Object[] row = new Object[2];

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = textAmount.getText();

                if (isValidAmount(amountText)) {
                    row[0] = amountText;
                    row[1] = textLabel.getText();
                    model.addRow(row);

                    textAmount.setText("");
                    textLabel.setText("");
                } else {
                    showError("Please enter a valid numeric amount.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.removeRow(i);
                } else {
                    showError("Delete Error");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                textAmount.setText(model.getValueAt(i, 0).toString());
                textLabel.setText(model.getValueAt(i, 1).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();

                if (i >= 0) {
                    String amountText = textAmount.getText();
                    if (isValidAmount(amountText)) {
                        model.setValueAt(amountText, i, 0);
                        model.setValueAt(textLabel.getText(), i, 1);
                    } else {
                        showError("Please enter a valid numeric amount.");
                    }
                } else {
                    showError("Update Error");
                }
            }
        });
    }

    public static void showIncomeDialog(JFrame parentFrame) {
        JDialog incomeDia = new JDialog(parentFrame, "Income", true);
        incomeDia.setSize(1000, 700);
        incomeDia.setLayout(null);
        incomeDia.setLocationRelativeTo(parentFrame);

        JLabel label = new JLabel("Income");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setBounds(15, 10, 200, 50);
        incomeDia.add(label);

        JButton backIncBtn = new JButton("Back");
        backIncBtn.setBounds(450, 600, 100, 50);
        backIncBtn.setBackground(Color.decode("#FF914D"));
        backIncBtn.setBorder(new LineBorder(Color.decode("#FF914D")));
        backIncBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        incomeDia.add(backIncBtn);
        backIncBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incomeDia.dispose();
            }
        });

        // Create a panel to hold the JTableRow functionality
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(200, 100, 750, 600); // Adjusted size
        setupTable(tablePanel);
        incomeDia.add(tablePanel);

        incomeDia.setVisible(true);
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
}
