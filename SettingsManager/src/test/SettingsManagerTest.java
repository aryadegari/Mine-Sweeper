package test;

import org.junit.Test;
import ui.GameLevel;
import ui.SettingsData;
import ui.SettingsManager;

import java.util.Random;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by hp on 24/01/2016.
 */
public class SettingsManagerTest {

    @Test
    public void saveLoadInitialTest() {

        SettingsManager settingsManager = SettingsManager.getInstance();
        SettingsData settings = new SettingsData();

        try {
            settingsManager.save(settings);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SettingsData loadedSettings = settingsManager.load();
        assertEquals(loadedSettings, settings);
    }

    @Test
    public void initialLoadTest() {
        SettingsManager settingsManager = SettingsManager.getInstance();
        SettingsData loadedSettings = settingsManager.load();
        SettingsData settings = new SettingsData();
        assertEquals(loadedSettings, settings);
    }

    @Test
    public void settingFieldsInitializedTest() {

        SettingsManager settingsManager = SettingsManager.getInstance();
        SettingsData loadedSettings = settingsManager.load();

        assertNotNull(loadedSettings.gameLevel);
        assertNotNull(loadedSettings.sound);
        assertNotNull(loadedSettings.language);
        assertNotNull(loadedSettings.theme);

        GameLevel gameLevel = new GameLevel(loadedSettings.gameLevel);

        assertTrue(gameLevel.cols > 0);
        assertTrue(gameLevel.rows > 0);
        assertTrue(gameLevel.mines > 0);
    }
}
