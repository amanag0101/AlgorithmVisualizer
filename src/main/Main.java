package main;

public class Main {
    private static final String APP_NAME = "Algorithm Visualizer";
    public static void main(String[] args) {
        AppFrame appFrame = new AppFrame(APP_NAME);
        //AppFrame appFrame = new AppFrame(APP_NAME, 800, 600);

        appFrame.setVisible(true);
    }
}
