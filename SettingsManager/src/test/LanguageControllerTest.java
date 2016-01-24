package test;

import core.LanguagesController;
import org.junit.Test;
import ui.SettingsData;

import java.util.ArrayList;

/**
 * Created by hp on 12/12/2015.
 */
public class LanguageControllerTest {

    @Test
    public void testWord() {

        SettingsData.Language[] languages = SettingsData.Language.values();
        ArrayList<LanguagesController> languageControllers = new ArrayList<>();

        for (SettingsData.Language language : languages) {
            languageControllers.add(new LanguagesController(language));
        }

        String[] input = {"Options", "NewGame", "Exit"};
        for (String word : input) {
            System.out.print("word:'" + word + "' ");
            for (LanguagesController languagesController : languageControllers) {
                System.out.print(languagesController.getLanguage() + ":'" + word + "' ");
            }
            System.out.println();
        }
    }
}
