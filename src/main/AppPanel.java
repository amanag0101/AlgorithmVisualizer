package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppPanel extends JPanel {
    AppPanel() {
        this.setLayout(new BorderLayout());

        // label properties
        JLabel label = new JLabel("Algorithm Visualizer");
        label.setFont(new Font("My Boli", Font.PLAIN, 50));
        label.setForeground(Color.BLUE);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label);
    }
}
