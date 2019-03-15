package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.CommandLine;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.GUI.GUIExecute;
import javafx.scene.control.Button;

import java.util.function.Consumer;

public class RunButton extends Button implements CommandExecutable, LanguageChangeable {

    public static final String RUN = "Run";

    private Language myLanguage;
    private CommandLine myTextBox;
    private Consumer<String> myCommandAccess;

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

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
    }
}
