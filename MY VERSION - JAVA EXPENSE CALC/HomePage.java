import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.text.NumberFormatter;


public class HomePage {

    //public instance of the main frame for calling it on the main file
    public JPanel homePanel;
    private Algo sharedVar;
    private JLabel view;

    public HomePage() {

        sharedVar = new Algo();
        sharedVar.initBudget = 0;

        //make the home page here
        homePanel = new JPanel();
        view = new JLabel("Current Budget: " + sharedVar.curBudget);
        view.setFont(new Font("Poppins", Font.PLAIN, 30));

        //button for adding init budget

        JButton add = new JButton("Declare Initial Budget");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog addDialog = new JDialog();
                addDialog.setTitle("Add an Account");
                addDialog.setSize(250, 120);
                addDialog.setLocationRelativeTo(null);
                addDialog.setLayout(new FlowLayout());

                // for number format only

                NumberFormat format = NumberFormat.getIntegerInstance();
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setValueClass(Integer.class);
                formatter.setMinimum(0); // Set minimum value allowed (if needed)
                formatter.setMaximum(Integer.MAX_VALUE); // Set maximum value allowed (if needed)
                formatter.setAllowsInvalid(false); // Allows only valid numbers

                //number format end ====

                JFormattedTextField userinp = new JFormattedTextField(formatter);
                userinp.setColumns(10);
                addDialog.add(userinp);

                JButton subButton = new JButton("Confirm");
                addDialog.add(subButton);

                JButton canButton = new JButton("Cancel");
                addDialog.add(canButton);

                subButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the input from the formatted text field
                        int newValue = (int) userinp.getValue();
    
                        // Set the sharedValue variable to the new value from the text field
                        sharedVar.curBudget = newValue;
    
                        // Display a confirmation message
                        JOptionPane.showMessageDialog(null, "Initial Balance set to: " + newValue);
                        updateBudgetLabel();
                        
    
                        // Close the dialog after confirmation
                        addDialog.dispose();
                    }
                });

                addDialog.setVisible(true);
            }
        });
        


        //Adding every buttons
        homePanel.add(add);
        homePanel.add(view);

        

    }

    private void updateBudgetLabel() {
        view.setText("Current Budget: " + sharedVar.curBudget);
    }
}
