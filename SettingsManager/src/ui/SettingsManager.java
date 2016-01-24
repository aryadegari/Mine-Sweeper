package ui;

import utils.Consts;
import utils.FileUtils;

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

    public void save(SettingsData settingsData) throws Exception {

        if (settingsData == null || settingsData.equals(SettingsManager.settingsData)) return;
        FileUtils.writeObject(settingsData, Consts.SETTINGS_FILE_PATH);
        SettingsManager.settingsData = settingsData;
    }

    public SettingsData load() {
        if (settingsData == null) {
            try {
                settingsData = (SettingsData) FileUtils.readObject(Consts.SETTINGS_FILE_PATH);
            } catch (Exception e) {
                System.out.println("SettingsManager.load : Cannot load Settings Data");
                settingsData = new SettingsData();
            }
        }
        return settingsData;
    }
}
