import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.*;


public class HomePage {

    //public instance of the main frame for calling it on the main file
    public JPanel homePanel;

    public HomePage() {

        Algo sharedVar = new Algo();
        sharedVar.initBudget = 0;

        //make the home page here
        homePanel = new JPanel();
        JLabel hi = new JLabel("HI This is Home Panel");

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



                JTextField userinp = new JTextField(15);
                addDialog.add(userinp);

                JButton subButton = new JButton("Confirm");
                addDialog.add(subButton);

                JButton canButton = new JButton("Cancel");
                addDialog.add(canButton);

                addDialog.setVisible(true);
            }
        });
        




        homePanel.add(add);
        homePanel.add(hi);

        


    //for MENU
    }
    
}
