package test;

import data.GameLevel;
import data.IntermediateLevel;
import org.junit.Before;
import org.junit.Test;
import ui.AccountInfo;
import ui.AccountManager;
import ui.SettingsData;
import ui.SettingsManager;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by hp on 24/01/2016.
 */
public class SettingsManagerTest {

    @Before
    public void setUp() throws Exception {
        AccountManager.getInstance().setAccountInfo(new AccountInfo("guest", "guest"));
    }

    @Test
    public void saveLoadInitialTest() {

        SettingsManager settingsManager = SettingsManager.getInstance();
        SettingsData settings = new SettingsData(SettingsData.Language.Fa, true, new GameLevel(new IntermediateLevel()), SettingsData.Theme.Modern);

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

        GameLevel gameLevel = new GameLevel(loadedSettings.gameLevel.level);

        assertTrue(gameLevel.level.cols > 0);
        assertTrue(gameLevel.level.rows > 0);
        assertTrue(gameLevel.level.mines > 0);
    }
}
