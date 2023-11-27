import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RoundedButtonGoal extends JButton {
    public RoundedButtonGoal(String text) {
        super(text);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setHoverBackground();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setDefaultBackground();
            }
        });
    }

    private void setHoverBackground() {
        setBackground(Color.decode("#FF914D")); // Set the hover background color to #FF914D
        repaint();
    }

    private void setDefaultBackground() {
        setBackground(Color.decode("#FFFFFF")); // Set the default background color
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable antialiasing for smoother edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2d.setColor(Color.decode("#FF914D")); // Set the color when the button is pressed
        } else {
            g2d.setColor(getBackground());
        }

        int width = getWidth();
        int height = getHeight();
        int cornerRadius = 15;

        g2d.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Dispose of the Graphics2D object
        g2d.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable antialiasing for smoother edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getForeground());

        int width = getWidth();
        int height = getHeight();
        int cornerRadius = 15;

        g2d.drawRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        // Dispose of the Graphics2D object
        g2d.dispose();
    }
}
