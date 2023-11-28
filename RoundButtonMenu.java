import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
class RoundButtonMenu extends JButton {

    public RoundButtonMenu(String text) {
        super(text);
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
        setBackground(Color.decode("#FF914D"));
        repaint();
    }

    private void setDefaultBackground() {
        setBackground(Color.decode("#FFFFFF"));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(Color.decode("#FF914D"));
        } else {
            g2.setColor(getBackground());
        }

        int arc = 20;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g);
        g2.dispose();
    }
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g.setColor(Color.decode("#FF914D"));
        } else {
            g.setColor(getBackground());
        }
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

        g2d.draw(roundedRectangle);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 40);  // Set the preferred size of the button
    }
}
