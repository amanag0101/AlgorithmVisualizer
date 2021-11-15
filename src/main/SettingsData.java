package main;
// Singleton Class
// syncs settings data across the application

public class SettingsData {
    private static SettingsData settingsData = null;
    private int barWidth, delay;

    private SettingsData() {
        barWidth = 20;
        delay = 0;  
    }

    public static SettingsData getInstance() {
        if(settingsData == null)
            settingsData = new SettingsData();

        return settingsData;
    }

    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getBarWidth() {
        return barWidth;
    }

    public int getDelay() {
        return delay;
    }    
}
