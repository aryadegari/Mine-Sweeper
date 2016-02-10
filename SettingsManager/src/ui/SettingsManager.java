package ui;

/**
 * Created by hp on 09/12/2015.
 */
public class SettingsManager {

    private static SettingsManager ourInstance = new SettingsManager();
    private static SettingsData settingsData;

    private SettingsManager() {
    }

    public static SettingsManager getInstance() {
        return ourInstance;
    }

    public void save(SettingsData settingsData) {

        if (settingsData == null || settingsData.equals(SettingsManager.settingsData)) return;
        settingsData.save();
        SettingsManager.settingsData = settingsData;
    }

    public SettingsData load() {
        if (settingsData == null) {
            try {
                settingsData = SettingsData.load();
            } catch (Exception e) {
                System.out.println("SettingsManager.load : Cannot load Settings Data");
                e.printStackTrace();
                settingsData = new SettingsData();
            }
        }
        return settingsData;
    }

}
