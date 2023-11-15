import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class mainpage {
    int id;
    

    public void mainPageView(int id) throws SQLException {
        this.id = id;

        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JPanel addPanel = new JPanel();
        addPanel.setBounds(250, 0, 750, 800); // Adjusted size
        addPanel.setLayout(null);

        JPanel optionPanel = new JPanel();
        optionPanel.setBounds(0, 0, 250, 800);
        optionPanel.setBackground(Color.decode("#2c2a2a"));
        optionPanel.setLayout(null);
        frame.add(optionPanel);

        JButton home = new JButton("HOME");
        home.setBounds(50, 100, 150, 50);
        home.setBackground(Color.decode("#FFFFFF"));
        home.setBorder(new LineBorder(Color.decode("#FFFFFF")));
        home.setForeground(Color.decode("#FF914D"));
        optionPanel.add(home);
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPanel.setVisible(true);
            }
        });

        JButton expenseBtn = new JButton("EXPENSE");
        expenseBtn.setBounds(50, 200, 150, 50);
        expenseBtn.setBackground(Color.decode("#FFFFFF"));
        expenseBtn.setBorder(new LineBorder(Color.decode("#FFFFFF")));
        expenseBtn.setForeground(Color.decode("#FF914D"));
        optionPanel.add(expenseBtn);
        expenseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExpenseDialog expenseDialog = new ExpenseDialog();
                expenseDialog.showExpenseDialog(frame);
            }
        });

        JButton incomeBtn = new JButton("INCOME");
        incomeBtn.setBounds(50, 300, 150, 50);
        incomeBtn.setBackground(Color.decode("#FFFFFF"));
        incomeBtn.setBorder(new LineBorder(Color.decode("#FFFFFF")));
        incomeBtn.setForeground(Color.decode("#FF914D"));
        optionPanel.add(incomeBtn);
        incomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeDialog incomeDialog = new IncomeDialog();
                incomeDialog.showIncomeDialog(frame);
            }
        });

        JButton goalsBtn = new JButton("GOALS");
        goalsBtn.setBounds(50, 400, 150, 50);
        goalsBtn.setBackground(Color.decode("#FFFFFF"));
        goalsBtn.setBorder(new LineBorder(Color.decode("#FFFFFF")));
        goalsBtn.setForeground(Color.decode("#FF914D"));
        optionPanel.add(goalsBtn);
        goalsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show dialogue box
                GoalsDialog goalsDialog = new GoalsDialog();
                goalsDialog.showGoalsDialog(frame);
            }
        });

        JButton logout = new JButton("LOGOUT");
        logout.setBounds(50, 500, 150, 50);
        logout.setBackground(Color.decode("#FFFFFF"));
        logout.setBorder(new LineBorder(Color.decode("#FFFFFF")));
        logout.setForeground(Color.decode("#FF914D"));
        optionPanel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}

class ExpenseDialog {

    public void showExpenseDialog(JFrame parentFrame) {
        JDialog expenseDia = new JDialog(parentFrame, "Expense", true);
        expenseDia.setSize(1000, 800);
        expenseDia.setLayout(null);
        expenseDia.setLocationRelativeTo(parentFrame);

        JLabel label = new JLabel("Expense");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setBounds(15, 10, 200, 50);
        expenseDia.add(label);

    

        JButton backExpBtn = new JButton("Back");
        backExpBtn.setBounds(450, 700, 100, 50);
        backExpBtn.setBackground(Color.decode("#FF914D"));
        backExpBtn.setBorder(new LineBorder(Color.decode("#FF914D")));
        backExpBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        expenseDia.add(backExpBtn);
        backExpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expenseDia.dispose();
            }
        });

        // Create a panel to hold the JTableRow functionality
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(200, 100, 750, 600); // Adjusted size
        JTableRowExpense.setupTable(tablePanel);
        expenseDia.add(tablePanel);

        expenseDia.setVisible(true);

    }
}

class IncomeDialog {

    public void showIncomeDialog(JFrame parentFrame) {
                JDialog incomeDia = new JDialog();
                incomeDia.setSize(1000, 800);
                incomeDia.setLayout(null);
                incomeDia.setLocationRelativeTo(null);

                JLabel label = new JLabel("Income");
                label.setFont(new Font("Arial", Font.PLAIN, 30));
                label.setBounds(15, 10, 200, 50);
                incomeDia.add(label);

                JButton backIncBtn = new JButton("Back");
                backIncBtn.setBounds(450, 700, 100, 50);
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

                incomeDia.setVisible(true);
     }
}

class GoalsDialog {

    public void showGoalsDialog(JFrame parentFrame) {
                JDialog goalsDia = new JDialog();
                goalsDia.setSize(1000, 800);
                goalsDia.setLayout(null);
                goalsDia.setLocationRelativeTo(null);

                JLabel label = new JLabel("Goals");
                label.setFont(new Font("Arial", Font.PLAIN, 30));
                label.setBounds(15, 10, 200, 50);
                goalsDia.add(label);

                JButton backGoalBtn = new JButton("Back");
                backGoalBtn.setBounds(450, 700, 100, 50);
                backGoalBtn.setBackground(Color.decode("#FF914D"));
                backGoalBtn.setBorder(new LineBorder(Color.decode("#FF914D")));
                goalsDia.add(backGoalBtn);
                backGoalBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        goalsDia.dispose();
                    }
                });

                goalsDia.setVisible(true);
    }
}

