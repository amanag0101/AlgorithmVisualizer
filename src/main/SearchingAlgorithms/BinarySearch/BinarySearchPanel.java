package main.SearchingAlgorithms.BinarySearch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import main.SettingsData;

public class BinarySearchPanel extends JPanel implements ActionListener {
    private final int FRAME_WIDTH, FRAME_HEIGHT;
    private SettingsData settingsData;
    private int barWidth, delay;

    private int[] data;
    private int searchElementIndex, searchElement;

    private Timer timer;
    private boolean isInitialArrayPainted = false;

    private int start = 0, end;
    private boolean isElementFound = false;

    public BinarySearchPanel(int screenWidth, int screenHeight) {
        this.setBackground(Color.BLACK);

        FRAME_WIDTH = screenWidth;
        FRAME_HEIGHT = screenHeight;

        settingsData = SettingsData.getInstance();
        barWidth = settingsData.getBarWidth();
        delay = settingsData.getDelay();

        if(delay == 0)
            delay = 1000;

        timer = new Timer(delay, this);
        timer.start();

        setData();
    }

    private void setData() {
        Random rand = new Random();
        data = new int[FRAME_WIDTH / barWidth];

        for(int i = 0; i < data.length; i++)
            data[i] = rand.nextInt(FRAME_HEIGHT - 50);

        sortArray();

        // sets the element to be searched
        searchElementIndex = rand.nextInt(data.length);
        searchElement = data[searchElementIndex];
        
        end = data.length - 1;
    }

    private void sortArray() {
        for(int i = 0; i < data.length - 1; i++) {
            for(int j = 0; j < data.length - 1 - i; j++) {
                if(data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(!isInitialArrayPainted) {
            for(int i = 0; i < data.length; i++) {
                if(i == searchElementIndex) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * barWidth, 0, barWidth, data[i]);
                    g.setColor(Color.BLUE);
                    g.drawRect(i * barWidth, 0, barWidth, data[i]);
                }
                else {
                    g.setColor(Color.CYAN);
                    g.fillRect(i * barWidth, 0, barWidth, data[i]);
                    g.setColor(Color.BLUE);
                    g.drawRect(i * barWidth, 0, barWidth, data[i]);
                }
            }
            isInitialArrayPainted = true;
        }
        else {
            for(int i = 0; i < data.length; i++) {
                // Green Color (change the yellow bar to green color when the element is found)
                if(i == searchElementIndex && isElementFound) {
                    g.setColor(Color.GREEN);
                    g.fillRect(i * barWidth, 0, barWidth, data[i]);
                    g.setColor(Color.BLUE);
                    g.drawRect(i * barWidth, 0, barWidth, data[i]);
                }
                // Yellow Color (bar which needs to be found)
                else if(i == searchElementIndex) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * barWidth, 0, barWidth, data[i]);
                    g.setColor(Color.BLUE);
                    g.drawRect(i * barWidth, 0, barWidth, data[i]);
                }
                else if(i >= start && i <= end) {
                    // Blue Color (bars in which search is happening)
                    g.setColor(Color.CYAN);
                    g.fillRect(i * barWidth, 0, barWidth, data[i]);
                    g.setColor(Color.BLUE);
                    g.drawRect(i * barWidth, 0, barWidth, data[i]);
                }
                else {
                    // Red Color (bars not needed)
                    g.setColor(Color.RED);
                    g.fillRect(i * barWidth, 0, barWidth, data[i]);
                    g.setColor(Color.BLUE);
                    g.drawRect(i * barWidth, 0, barWidth, data[i]);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // binary search algorithm
        if(start <= end) {
            int mid = (start + end) / 2;

            if(data[mid] == searchElement) {
                isElementFound = true;
                timer.stop();
            }
            else if(searchElement > data[mid])
                start = ++mid;
            else
                end = --mid;

        }
        else {
            timer.stop();
        }

        repaint();
    }
}
