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

    public IncomePage () {

        incomePanel = new JPanel();
        JLabel hi = new JLabel("HI This is Income Panel");
        incomePanel.add(hi);

    }
    
}
