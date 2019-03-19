package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.function.Consumer;

/**
 * Class that implements the functionality of the clear button.
 * Author: Louis Jensen, Ryan Culhane
 */
public class ClearButton extends Button implements CommandExecutable, LanguageChangeable {

    private static final String CLEAR_BUTTON_NAME = "Clear";
    private static final String CLEAR_SCREEN = "ClearScreen";
    private Consumer<String> myCommandAccess;
    private Language myLanguage;

    /**
     * Constructor for the clear button.  The ClearButton needs access to the commandline because it needs to be able
     * to clear the text in the commandline
     * @param commandLine access to the text area to be cleared when the clear button is pressed
     */
    public ClearButton(TextArea commandLine){
        this.setText(CLEAR_BUTTON_NAME);
        myLanguage = Language.ENGLISH;
        this.setOnAction(event -> {
            commandLine.setText("");
            runCommand(CLEAR_SCREEN);
        });
    }

    /**
     * Gives the class the ability to run commands by passing a consumer that takes a String and passes
     * the command to the parser to be run through the backend and have its effects displayed on the front end as
     * well as stored in the backend
     * @param commandAccess a consumer that feeds the command to the parser to run the command through the backend.
     */
    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    /**
     * Method that calls the accept method on the consumer that was passed in the giveAbilityToRunCommands method
     * @param command the command to be run
     */
    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(myLanguage.getTranslatedWord(command));
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        setText(newLanguage.getTranslatedWord(CLEAR_BUTTON_NAME));
    }

}
