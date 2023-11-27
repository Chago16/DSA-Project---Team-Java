import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RoundedTextField extends JTextField {
    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 20;

    public RoundedTextField() {
        setOpaque(false);
        setBorder(new RoundedCornerBorder());
        setPreferredSize(new Dimension(200, 30)); // Set your preferred size here
        setBackground(Color.decode("#FFFFFF"));
        // Set a smaller font size
        Font smallerFont = getFont().deriveFont(12f); // Adjust the font size as needed
        setFont(smallerFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
            g2.dispose();
        }
        super.paintComponent(g);
    }

    private class RoundedCornerBorder extends EmptyBorder {
        RoundedCornerBorder() {
            super(0, 0, 0, 0);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(x, y, width - 1, height - 1, ARC_WIDTH, ARC_HEIGHT);
            g2.dispose();
        }
    }
}
