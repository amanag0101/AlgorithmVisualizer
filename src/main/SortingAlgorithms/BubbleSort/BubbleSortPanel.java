package main.SortingAlgorithms.BubbleSort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.SettingsData;

public class BubbleSortPanel extends JPanel implements ActionListener {
    private final int FRAME_WIDTH, FRAME_HEIGHT;
    
    private SettingsData settingsData;
    private int barWidth;     // width of each bar
    private int delay;        // delay in action

    private Timer timer;
    private boolean isInitialArrayPainted = false;

    private int data[];     // data array
    private int index_i = 0, index_j = 0;       // bubble sort algorithm (iterators)
    private int colorRedIndex = -1, colorYellowIndex = -1;      // used to change the color of the bar of an index

    // Constructor
    public BubbleSortPanel(int frameWidth, int frameHeight) {
        this.setBackground(Color.BLACK);
        
        FRAME_WIDTH = frameWidth;
        FRAME_HEIGHT = frameHeight;

        settingsData = SettingsData.getInstance();
        barWidth = settingsData.getBarWidth();
        delay = settingsData.getDelay();

        timer = new Timer(delay, this);
        timer.start();

        data = new int[FRAME_WIDTH / barWidth];
        setData();
    }

    // sets the data
    private void setData() {
        Random rand = new Random();

        for(int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(FRAME_HEIGHT - 50);
        }
    }

    //paints the screen
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(!isInitialArrayPainted) {
            for(int i = 0; i < data.length; i++) {
                g.setColor(Color.CYAN);
                g.fillRect(i * barWidth, 0, barWidth, data[i]);
                g.setColor(Color.BLUE);
                g.drawRect(i * barWidth, 0, barWidth, data[i]);
            }
            isInitialArrayPainted = true;
        }
        else {
            for(int i = 0; i < data.length; i++) {
                if(i == colorYellowIndex) 
                    g.setColor(Color.YELLOW);
                else if(i == colorRedIndex)
                    g.setColor(Color.RED);
                else
                    g.setColor(Color.CYAN);
                
                g.fillRect(i * barWidth, 0, barWidth, data[i]);
                g.setColor(Color.BLUE);
                g.drawRect(i * barWidth, 0, barWidth, data[i]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // bubble sort algorithm
        if(index_i < data.length - 1) {
            if(index_j < data.length - 1 - index_i) {
                if(data[index_j] > data[index_j + 1]) {
                    int temp = data[index_j];
                    data[index_j] = data[index_j + 1];
                    data[index_j + 1] = temp;

                    colorYellowIndex = index_j;
                    colorRedIndex = index_j + 1;
                }

                index_j++;            
            }
            else {
                index_i++;
                index_j = 0;
            }
        }
        else {
            timer.stop();
        }

        repaint();
    }
}
