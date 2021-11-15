package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SettingsPanel extends JPanel implements ActionListener {
    private JLabel barWidth_label, delay_label;
    private JSpinner barWidth_spinner, delay_spinner;
    private JButton save_bt;

    private SettingsData settingsData;

    public SettingsPanel() {
        settingsData = SettingsData.getInstance();
        
        barWidth_label = new JLabel("Bar Width (1, 40]");
        this.add(barWidth_label);

        barWidth_spinner = new JSpinner(new SpinnerNumberModel(20, 2, 40, 1));   // initial, min, max, step
        this.add(barWidth_spinner);

        delay_label = new JLabel("Delay (milliseconds)");
        this.add(delay_label);

        delay_spinner = new JSpinner(new SpinnerNumberModel(0, 0, 5000, 100));
        this.add(delay_spinner);

        barWidth_spinner.setValue(settingsData.getBarWidth());
        delay_spinner.setValue(settingsData.getDelay());

        save_bt = new JButton("Save");    
        save_bt.addActionListener(this);    
        this.add(save_bt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save_bt) {
            int barWidth = (int) barWidth_spinner.getValue();
            int delay = (int) delay_spinner.getValue();

            settingsData.setBarWidth(barWidth);
            settingsData.setDelay(delay);
        }
    }
}
