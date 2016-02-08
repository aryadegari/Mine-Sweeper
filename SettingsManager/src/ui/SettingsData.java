package ui;

import com.google.gson.Gson;
import core.LanguagesController;
import data.BeginnerLevel;
import data.GameLevel;

import java.util.prefs.Preferences;

/**
 * Created by hp on 09/12/2015.
 */
public class SettingsData {

    private static final String SETTINGS_PREFIX = "settings_data_";
    private static final String LANGUAGE_KEY = SETTINGS_PREFIX + "language_key_";
    private static final String SOUND_KEY = SETTINGS_PREFIX + "sound_key_";
    private static final String GAME_LEVEL_KEY = SETTINGS_PREFIX + "game_level_key_";
    private static final String THEME_KEY = SETTINGS_PREFIX + "theme_key_";

    public Language language = Language.En;
    public boolean sound = false;
    public GameLevel gameLevel = new GameLevel(new BeginnerLevel());
    public Theme theme = Theme.Modern;
    private LanguagesController languagesController;

    public SettingsData() {
    }

    public SettingsData(Language language, boolean sound, GameLevel gameLevel, Theme theme) {
        this.language = language;
        this.sound = sound;
        this.gameLevel = gameLevel;
        this.theme = theme;
    }

    private static Preferences getPreferences() {
        return Preferences.userRoot().node(SettingsData.class.getName());
    }

    public static SettingsData load() {
        SettingsData settings = new SettingsData();
        Preferences prefs = getPreferences();
        AccountInfo accountInfo = AccountManager.getInstance().getAccountInfo();
        if (accountInfo == null) {
            System.out.println("SettingsData.save : no account set! don't save.");
            return settings;
        }
        String username = accountInfo.username;
        settings.language = Language.values()[prefs.getInt(LANGUAGE_KEY + username, settings.language.ordinal())];
        settings.sound = prefs.getBoolean(SOUND_KEY + username, settings.sound);
        settings.gameLevel = GameLevel.fromJson(prefs.get(GAME_LEVEL_KEY + username, new Gson().toJson(settings.gameLevel)));
        settings.theme = Theme.values()[prefs.getInt(THEME_KEY + username, settings.theme.ordinal())];

        return settings;
    }

    public LanguagesController getLanguagesController() {
        if (languagesController == null) {
            languagesController = new LanguagesController(language);
        }
        return languagesController;
    }

    public void save() {
        Preferences prefs = getPreferences();
        AccountInfo accountInfo = AccountManager.getInstance().getAccountInfo();
        if (accountInfo == null) {
            System.out.println("SettingsData.save : no account set! don't save.");
            return;
        }
        String username = accountInfo.username;
        prefs.putInt(LANGUAGE_KEY + username, language.ordinal());
        prefs.putBoolean(SOUND_KEY + username, sound);
        prefs.put(GAME_LEVEL_KEY + username, new Gson().toJson(gameLevel));
        prefs.putInt(THEME_KEY + username, theme.ordinal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingsData that = (SettingsData) o;

        if (sound != that.sound) return false;
        if (language != that.language) return false;
        if (!gameLevel.equals(that.gameLevel)) return false;
        return theme == that.theme;
    }

    @Override
    public String toString() {
        return "SettingsData{" +
                "language=" + language +
                ", sound=" + sound +
                ", gameLevel=" + gameLevel +
                ", theme=" + theme +
                '}';
    }

    public enum Language {

        En("English"), Fa("Persian");

        private String languageText;

        Language(String languageText) {
            this.languageText = languageText;
        }

        @Override
        public String toString() {
            return languageText;
        }

        public String[] getItems() {
            String[] items = new String[values().length];
            int index = 0;
            for (Language item : Language.values()) {
                items[index] = item.toString();
                index++;
            }
            return items;
        }
    }

    public enum Theme {

        Modern, Classic, Cartoon;

        public static String[] getItems() {
            String[] items = new String[values().length];
            int index = 0;
            for (Theme item : Theme.values()) {
                items[index] = item.toString();
                index++;
            }
            return items;
        }
    }
}
