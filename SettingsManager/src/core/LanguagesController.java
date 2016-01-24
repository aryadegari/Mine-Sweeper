package core;

import ui.SettingsData;
import utils.Consts;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by hp on 12/12/2015.
 */
public class LanguagesController {

    private SettingsData.Language language;
    private ResourceBundle translation;

    public LanguagesController(SettingsData.Language language) {

        this.language = language;
        Locale Persian = new Locale("fa", "IR", "fa");
        Map<SettingsData.Language, Locale> supportedLanguages = new HashMap<>();
        supportedLanguages.put(SettingsData.Language.Fa, Persian);
        supportedLanguages.put(SettingsData.Language.En, Locale.ENGLISH);
        translation = ResourceBundle.getBundle(Consts.LANGUAGES_BUNDLE_NAME, supportedLanguages.get(language));
    }

    public SettingsData.Language getLanguage() {
        return language;
    }

    public String getWord(String keyword) {
        return translation.getString(keyword);
    }
}
