import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashScreen extends JWindow {

    public SplashScreen() {
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setContentPane(content);

        JLabel label = new JLabel("Cofferbliss", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
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
