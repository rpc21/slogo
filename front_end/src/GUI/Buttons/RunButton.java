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

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(command);
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
    }
}
