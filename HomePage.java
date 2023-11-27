import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage {

    // public instance of the main frame for calling it on the main file
    public JPanel homePanel;
    public static JLabel availBalLabel;

    public HomePage() {
        // Load the Poppins font
        Font poppinsFont = loadPoppinsFont();

        // Make the home page here
        homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout()); // Set layout manager to BorderLayout
        JPanel dashboardPanel = new JPanel(new GridBagLayout());
        dashboardPanel.setBackground(Color.WHITE); // Changing background color to white

        // Creating a smaller rectangular panel inside the dashboard panel
        int width = 750;
        int height = 200;
        RoundedPanel availbudgPanel = new RoundedPanel(20, Color.decode("#FF914D")); // Using RoundedPanel
        availbudgPanel.setPreferredSize(new Dimension(width, height));

        // Adding the smaller panel to the dashboard panel using GridBagConstraints to center it
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        dashboardPanel.add(availbudgPanel, gbc);

        // Load the icon from the file
        ImageIcon budgetIcon = new ImageIcon("pictures/budget.png");

        // Create a JLabel for the icon
        JLabel iconLabel = new JLabel(budgetIcon);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(25, 10, 20, 10));

        // Creating "Available Budget" label
        JLabel availbudgLabel = new JLabel("Available Budget");
        availbudgLabel.setFont(poppinsFont.deriveFont(Font.BOLD, 25));
        availbudgLabel.setForeground(Color.decode("#FFFFFF"));
        availbudgLabel.setBorder(BorderFactory.createEmptyBorder(30, 5, 20, 30));

        // Set layout for availbudgPanel
        availbudgPanel.setLayout(new BorderLayout());
        availbudgPanel.add(iconLabel, BorderLayout.WEST);
        availbudgPanel.add(availbudgLabel, BorderLayout.CENTER);

        // Creating "Available Balance" label
        availBalLabel = new JLabel("Available Balance: 0.00");
        availBalLabel.setFont(poppinsFont.deriveFont(Font.BOLD, 20));
        availBalLabel.setForeground(Color.decode("#FFFFFF"));
        availbudgPanel.add(availBalLabel, BorderLayout.SOUTH);
        availBalLabel.setBorder(BorderFactory.createEmptyBorder(0, 400, 30, 30));

        // Adding the dashboard to the center of homePanel
        homePanel.add(dashboardPanel, BorderLayout.CENTER);
    }

    private Font loadPoppinsFont() {
        try {
            // Load Poppins font from the file
            return Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Poppins-Regular.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 12); // Fallback font
        }
    }

    public static void updateAvailableBalance() {
        Variables.pocketMoney = Variables.totalIncome - Variables.totalExpenses;
        Variables varUse = new Variables();
        varUse.updateToFile("Data.dat");
        availBalLabel.setText("Available Balance: " + Variables.pocketMoney);
    }
}

