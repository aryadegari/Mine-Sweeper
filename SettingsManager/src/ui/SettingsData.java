package ui;

import core.LanguagesController;

/**
 * Created by hp on 09/12/2015.
 */
public class SettingsData {

    public Language language = Language.En;
    public boolean sound = false;
    public GameLevel gameLevel = GameLevel.beginner();
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

    public LanguagesController getLanguagesController() {
        if (languagesController == null) {
            languagesController = new LanguagesController(language);
        }
        return languagesController;
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

    public enum Language {En, Fa}

    public enum Theme {Modern, Classic, Cartoon}
}
