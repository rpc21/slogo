package GUI.Buttons;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Abstract super class for the different choosers in the toolbar. Creates a menu of items from the properties file
 * specified by the createBundle() method that needs to be implemented by the subclass.  Sets actions for when the
 * menu items are clicked based on the consumer and how keys are processed for consumption based on the
 * processKeyForConsumption method that needs to be implemented by the subclass.
 * @param <T> the type that can be consumed by the consumer
 */
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
