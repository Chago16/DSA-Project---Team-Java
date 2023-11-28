import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

class RoundButtonMenu extends JButton {
    private static final int ARC_WIDTH = 15;
    private static final int ARC_HEIGHT = 15;

    public RoundButtonMenu(String label) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);

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
        
        Graphics2D g0 = (Graphics2D) g.create();
        g0.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g0.setColor(Color.decode("#FF914D"));  // Set the background color when the button is pressed
        } else {
            g0.setColor(getBackground()); // Set the background color when the button is not pressed
        }

        int arc = 20;  // Set the arc radius for rounded corners
        g0.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g);
        g0.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);

        // Draw the rounded rectangle border
        g2d.draw(roundedRectangle);
    }
}
