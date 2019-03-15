package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class PaletteChooser<T> extends MenuButton implements LanguageChangeable {

    protected ResourceBundle myBundle;
    protected Consumer<T> myConsumer;
    protected Language myLanguage;

    public PaletteChooser(Consumer<T> consumer){
        myConsumer = consumer;
        myLanguage = Language.ENGLISH;
        createBundle();
        buildChooser();
    }

    protected abstract void createBundle();

    private void buildChooser(){
        for (String key : Collections.list(myBundle.getKeys())){
            MenuItem menuItem = new MenuItem(myBundle.getString(key));
            menuItem.setOnAction(e -> {
                setText(myBundle.getString(key));
                myConsumer.accept(processKeyForConsumption(key));
            });
            this.getItems().add(menuItem);
        }
        setText(getItems().get(0).getText());
    }

    protected abstract T processKeyForConsumption(String key);

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage){
        myLanguage = newLanguage;
    }
}
