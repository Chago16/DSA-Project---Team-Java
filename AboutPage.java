import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class AboutPage {
    public JPanel aboutPanel;

    public AboutPage() {
        // make the about panel here
        aboutPanel = new JPanel();
        aboutPanel.setLayout(new BorderLayout()); // Set layout manager to BorderLayout
        aboutPanel.setBackground(Color.decode("#FFFFFF"));

        // Add "About" heading with a scaled icon
        JLabel aboutHeadingLabel = new JLabel("About");
        aboutHeadingLabel.setFont(new Font("Poppins", Font.PLAIN, 30));
        aboutHeadingLabel.setForeground(Color.WHITE);
        aboutHeadingLabel.setBorder(new EmptyBorder(10, 50, 10, 0));

        // Add the heading label to the top of the aboutPanel
        aboutPanel.add(aboutHeadingLabel, BorderLayout.NORTH);

        // Create a panel to hold the content
        JPanel aboutContentPanel = new JPanel(new GridBagLayout());
        aboutContentPanel.setBackground(Color.WHITE); // Changing background color to white

        // Add welcome message
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel welcomeLabel = new JLabel("<html><strong>Welcome to CofferBliss!</strong><br>Created to satisfy all your needs with simplicity and user-friendliness."
                + "Our mission is to enable you to effortlessly take control of your finances. If you want to monitor your personal expenses, setting a budget for your business, or tracking project expenses, CofferBliss is the right solution for you!</html>");
        
        welcomeLabel.setFont(new Font("Poppins", Font.PLAIN, 20)); // Adjust font size
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        aboutContentPanel.add(welcomeLabel, gbc);
        welcomeLabel.setPreferredSize(new java.awt.Dimension(700, 400)); // Adjust width and height as needed
        
        // Add team image
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Center the image

        ImageIcon teamImageIcon = createImageIcon("pictures/team.png", 700, 400);
        JLabel teamImageLabel = new JLabel(teamImageIcon);
        aboutContentPanel.add(teamImageLabel, gbc);

        //add whyCofferBliss text
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel whyCofferBliss = new JLabel("<html><strong>Why Choose CofferBliss:</strong>"
                +"<ul>"
                +"<li><strong>Budgeting Made Easy:</strong> Say goodbye to complicated spreadsheets and budgeting tools. CofferBliss makes budgeting easier, allowing you to focus on your financial goals.</li>"
                +"<li><strong>Easy Access Anytime, Anywhere:</strong> Enjoy the convenience of accessing CofferBliss whenever you have an internet connection. Manage your finances and track your costs from home, the workplace, or on the go.</li>"
                +"<li><strong>Create Custom Budget:</strong> Easily create budgets for different expense accounts. Swiftly allocate funds for personal needs such as groceries, entertainment, utilities, and more. Create budgets tailored to your needs!</li>"
                +"<li><strong>User-Friendly Interface:</strong> CofferBliss is designed with simplicity in mind. No jargon, no complicated terminology – just straightforward, easy-to-understand options that make budgeting a snap.</li>"
                +"<li><strong>Hands-on Support:</strong> Have a question or need assistance? Our committed support team is here to help. We are dedicated to offering excellent customer service, ensuring your experience with CofferBliss is seamless.</li>"
                +"</ul>"
                +"</html>"
        
        );
        whyCofferBliss.setFont(new Font("Poppins", Font.PLAIN, 17));// Adjust font size
        whyCofferBliss.setBorder(new EmptyBorder(100, 0, 100, 0));
        aboutContentPanel.add(whyCofferBliss, gbc);
        whyCofferBliss.setPreferredSize(new java.awt.Dimension(700, 700));

        //another image
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon anotherImageIcon = createImageIcon("pictures/outro.png", 700, 400);
        JLabel anotherImageLabel = new JLabel(anotherImageIcon);
        aboutContentPanel.add(anotherImageLabel, gbc);

        //add contactus
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;

	    JLabel contactUsLabel = new JLabel("<html><strong>Contact Us:</strong>"
	    +"<p>If you have any questions or need assistance, feel free to contact our support team.<br>"
	    + "Email: cofferbliss@gmail.com<br>"
        + "</html>");
       contactUsLabel.setFont(new Font("Poppins", Font.PLAIN, 17));// Adjust font size
       contactUsLabel.setBorder(new EmptyBorder(100, 0, 0, 0));
       aboutContentPanel.add(contactUsLabel, gbc);

        // Create a scroll pane and add the content panel to it
        JScrollPane scrollPane = new JScrollPane(aboutContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the aboutPanel
        aboutPanel.add(scrollPane, BorderLayout.CENTER);
        aboutPanel.setBackground(Color.decode("#FF914D"));

    }

    // Method to create an ImageIcon with explicit dimensions
    private ImageIcon createImageIcon(String path, int width, int height) {
        return new ImageIcon(getClass().getResource(path)) {
            @Override
            public int getIconWidth() {
                return width;
            }

            @Override
            public int getIconHeight() {
                return height;
            }
        };
    }
}
