import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class mainpage {
    int id;

    public void mainPageView(int id) throws SQLException {
        this.id = id;

        JFrame frame = new JFrame();
        frame.setSize(1000, 700);
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
                JTableRowExpense.showExpenseDialog(frame);
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
                JTableRowIncome.showIncomeDialog(frame);
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
                JTableRowGoals.showGoalsDialog(frame);
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
