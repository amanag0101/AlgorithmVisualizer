package main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.SearchingAlgorithms.BinarySearch.BinarySearchPanel;
import main.SortingAlgorithms.BubbleSort.BubbleSortPanel;
import main.SortingAlgorithms.SelectionSort.SelectionSortPanel;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GraphicsDevice;

public class AppFrame extends JFrame implements ActionListener { 
    private JMenuBar menuBar;
    private JMenu fileMenu, viewMenu, sortingAlgoMenu, searchingAlgoMenu;
    private JMenuItem homeItem, exitItem, settingsItem, bubbleSortItem, selectionSortItem, binarySearchItem;

    // constructor with frame_title and auto screen resolution
    public AppFrame(String frameTitle) {
        // sets the app theme
        setTheme();

        // Frame Properties
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // fullscreen mode (no title bar)
        if(myDevice.isFullScreenSupported()) {
            this.setUndecorated(true);
            myDevice.setFullScreenWindow(this);
        }
        // windowed mode (title bar present)
        else {
            int deviceWidth = myDevice.getDisplayMode().getWidth();
            int deviceHeight = myDevice.getDisplayMode().getHeight();
            
            this.setSize(deviceWidth, deviceHeight);
            this.setLocationRelativeTo(null);
        }

        // add other components
        addMenuBar();
        this.add(new AppPanel());
    }

    // constructor passed with app title, width and height
    public AppFrame(String frameTitle, int frameWidth, int frameHeight) {
        // sets the app theme
        setTheme();

        // Frame Properties
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);

        // add other components
        addMenuBar();
        this.add(new AppPanel());
    }

    // sets the theme of the application
    private static void setTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (InstantiationException e) {
            e.printStackTrace();
        } 
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    //  setting the JMenuBar
    private void addMenuBar() {
        menuBar = new JMenuBar();

        // menus
        fileMenu = new JMenu("File");
        viewMenu = new JMenu("View");
        sortingAlgoMenu = new JMenu("Sorting Algorithms");
        searchingAlgoMenu = new JMenu("Searching Algorithms");

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(sortingAlgoMenu);
        menuBar.add(searchingAlgoMenu);

        // menu items
        homeItem = new JMenuItem("Home");
        exitItem = new JMenuItem("Exit");
        settingsItem = new JMenuItem("Settings");
        bubbleSortItem = new JMenuItem("Bubble Sort");
        selectionSortItem = new JMenuItem("Selection Sort");
        binarySearchItem = new JMenuItem("Binary Search");

        fileMenu.add(homeItem);
        fileMenu.add(exitItem);
        viewMenu.add(settingsItem);
        sortingAlgoMenu.add(bubbleSortItem);
        sortingAlgoMenu.add(selectionSortItem);
        searchingAlgoMenu.add(binarySearchItem);

        // menu item on-click
        homeItem.addActionListener(this);
        exitItem.addActionListener(this);
        settingsItem.addActionListener(this);
        bubbleSortItem.addActionListener(this);
        selectionSortItem.addActionListener(this);
        binarySearchItem.addActionListener(this);

        this.setJMenuBar(menuBar);
    }

    // handle action events (on-click listeners)
    @Override
    public void actionPerformed(ActionEvent e) {
        // exit
        if(e.getSource() == exitItem)
            System.exit(0);

        // home
        if(e.getSource() == homeItem) {
            this.getContentPane().removeAll();
            this.add(new AppPanel());
            this.revalidate();
        }

        // settings
        if(e.getSource() == settingsItem) {
            this.getContentPane().removeAll();
            this.add(new SettingsPanel());
            this.revalidate();
        }

        // bubble sort
        if(e.getSource() == bubbleSortItem) {
            this.getContentPane().removeAll();
            this.add(new BubbleSortPanel(this.getWidth(), this.getHeight()));
            this.revalidate();
        }

        // selection sort
        if(e.getSource() == selectionSortItem) {
            this.getContentPane().removeAll();
            this.add(new SelectionSortPanel(this.getWidth(), this.getHeight()));
            this.revalidate();
        }

        // binary search
        if(e.getSource() == binarySearchItem) {
            this.getContentPane().removeAll();
            this.add(new BinarySearchPanel(this.getWidth(), this.getHeight()));
            this.revalidate();
        }
    }
}