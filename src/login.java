import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class login {
    int id;

    public void loginView() throws SQLException {
        SQLoperations manage = new SQLoperations();
        JFrame frame = new JFrame();
        frame.setSize(700, 550);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#FFFFFF"));
        frame.setLocationRelativeTo(null);

        JLabel heading = new JLabel("CofferBliss");
        heading.setBounds(0, 50, 700, 50);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));
        frame.add(heading);

        JLabel uname = new JLabel("Username : ");
        uname.setBounds(175, 130, 150, 50);
        frame.add(uname);

        JTextField name = new JTextField();
        name.setBounds(175, 170, 350, 30);
        frame.add(name);

        JLabel upass = new JLabel("Password : ");
        upass.setBounds(175, 200, 150, 50);
        frame.add(upass);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(175, 240, 350, 30);
        frame.add(pass);

        /*Login section */
        JButton login = new JButton("LOGIN");
        login.setBounds(225, 300, 100, 40);
        login.setBackground(Color.decode("#FF914D"));
        login.setBorder(new LineBorder (Color.decode("#FF914D")));
        frame.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = name.getText();
                String password = new String(pass.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please Enter All Information.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        SQLoperations manage = new SQLoperations();
                        id = manage.authUser(username, password);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if (id == -1) {
                        JOptionPane.showMessageDialog(frame, "User Does Not Exist.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    } else if (id == 0) {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password, please try again.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                    } else {
                        mainpage mainPage = new mainpage();
                        try {
                            mainPage.mainPageView(id);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        frame.dispose();
                    }
                }
            }
        });

        /*Signup section option */
        JButton signUp = new JButton("SIGNUP");
        signUp.setBounds(375, 300, 100, 40);
        signUp.setBackground(Color.decode("#FF914D"));
        signUp.setBorder(new LineBorder (Color.decode("#FF914D")));
        frame.add(signUp);
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup signup = new signup();
                signup.signUpView();
            }
        });

        frame.setVisible(true);
    }
}
