import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class RoundedOrangeButton extends JButton {

    public RoundedOrangeButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setBorder(new LineBorder(Color.decode("#FF914D")));

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
        setBackground(Color.decode("#DDDDD0")); // Set the hover background color to #FF914D
        repaint();
    }

    private void setDefaultBackground() {
        setBackground(Color.decode("#FF914D")); // Set the default background color
        repaint();

    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(Color.decode("#DDDDD0"));  // Set the background color when the button is pressed
        } else {
            g2.setColor(getBackground()); // Set the background color when the button is not pressed
        }

        int arc = 20;  // Set the arc radius for rounded corners
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 40);  // Set the preferred size of the button
    }
}
