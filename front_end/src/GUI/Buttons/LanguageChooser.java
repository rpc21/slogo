package GUI.Buttons;

import GUI.Commands.Language;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Class that creates a menu of available languages and will change the language of the application and parser when a
 * language is selected
 */
public class LanguageChooser extends PaletteChooser{

    private static final String LANGUAGES_FILE = "Languages";

    public LanguageChooser(Consumer<Language> consumer){
        super(consumer);
    }

    @Override
    protected void createBundle() {
        myBundle = ResourceBundle.getBundle(LANGUAGES_FILE);
    }

    @Override
    protected Language processKeyForConsumption(String key) {
        return Language.valueOf(key.toUpperCase());
    }
}
