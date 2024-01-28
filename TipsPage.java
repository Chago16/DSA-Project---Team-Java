import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TipsPage {
    public JPanel tipsPanel;
    private JLabel imageLabel;
    private Timer slideshowTimer;
    private int currentImageIndex;
    private String[] imageNames = {"1.png", "2.png", "3.png", "4.png", "5.png"};
    private String folderPath = "slideshowpics/";

    public TipsPage() {
        tipsPanel = new JPanel();
        tipsPanel.setLayout(new BorderLayout());
        tipsPanel.setBackground(Color.decode("#FFFFFF"));

        imageLabel = new JLabel();
        tipsPanel.add(imageLabel, BorderLayout.CENTER);

        // Set initial image
        currentImageIndex = 0;
        updateImage(imageLabel, folderPath + imageNames[currentImageIndex]);

        // Initialize timer
        slideshowTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        // Start the slideshow timer
        slideshowTimer.start();

        JButton prevButton = createRoundedButton("<");
        prevButton.setFont(new Font("Poppins", Font.PLAIN, 24));
        prevButton.setBackground(Color.decode("#FF914D"));

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });

        JButton nextButton = createRoundedButton(">");
        nextButton.setFont(new Font("Poppins", Font.PLAIN, 24));
        nextButton.setBackground(Color.decode("#FF914D"));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        tipsPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        // Initialize timer and set initial image
        currentImageIndex = 0;
        updateImage(imageLabel, folderPath + imageNames[currentImageIndex]);

        slideshowTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        // Start the slideshow timer
        slideshowTimer.start();
    }

    // Helper method to update the image label
    private void updateImage(JLabel label, String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));

        // Check for non-zero dimensions before updating the image
        int width = label.getWidth();
        int height = label.getHeight();
        if (width > 0 && height > 0) {
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
    }
    
    private JButton createRoundedButton(String text) {
        JButton button = new RoundedOrangeButton(text);
        button.setFocusPainted(false);
        return button;
    }

    private void showNextImage() {
        currentImageIndex = (currentImageIndex + 1) % imageNames.length;
        updateImage(imageLabel, folderPath + imageNames[currentImageIndex]);
    }

    private void showPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + imageNames.length) % imageNames.length;
        updateImage(imageLabel, folderPath + imageNames[currentImageIndex]);
    }

    public JPanel getTipsPanel() {
        return tipsPanel;
    }
}
