import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        JPanel content = new JPanel(new BorderLayout());
        
        // Set the orange line border
        content.setBorder(new LineBorder(Color.decode("#FF914D"), 1));
        setContentPane(content);

        // Load the image from file
        ImageIcon icon = new ImageIcon("Logo.png");
        JLabel label = new JLabel(icon);
        content.add(label, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public void showSplashScreen(int duration) {
        setVisible(true);

        try {
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
    }
}
