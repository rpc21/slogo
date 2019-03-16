package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.CommandLine;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIExecute;
import javafx.scene.control.Button;

import java.util.function.Consumer;

/**
 * Class the implements the functionality of the run button.  This class is able to access the commandline and
 * retrieve the text from the commandline when clicked and send the command to the parser.
 */
public class RunButton extends Button implements CommandExecutable, LanguageChangeable {

    private static final String RUN = "Run";

    private Language myLanguage;
    private CommandLine myTextBox;
    private Consumer<String> myCommandAccess;

    /**
     * Constructor for the RunButton, establishes the preferences for the RunButton
     * @param textBox gives RunButton access to its corresponding command line
     */
    public RunButton(CommandLine textBox) {
        super();
        myLanguage = Language.ENGLISH;
        setText(myLanguage.getTranslatedWord(RUN));
        myTextBox = textBox;
        setOnMouseClicked(event -> myCommandAccess.accept(myTextBox.getText()));
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
        myCommandAccess.accept(command);
    }

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
    }
}
