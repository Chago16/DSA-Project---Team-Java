import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class TipsPage {
    public JPanel tipsPanel;
    
    public TipsPage() {
    // make the goal panel here
    tipsPanel = new JPanel();
    tipsPanel.setLayout(new BorderLayout()); // Set layout manager to BorderLayout
    tipsPanel.setBackground(Color.decode("#FFFFFF"));
    JPanel tipsContentPanel = new JPanel(new GridBagLayout());
    tipsContentPanel.setBackground(Color.WHITE); // Changing background color to white


    }
    
}