package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.function.Consumer;

public class ClearButton extends Button implements CommandExecutable, LanguageChangeable{

    private static final String CLEAR_BUTTON_NAME = "Clear";
    public static final String CLEAR_SCREEN = "ClearScreen";
    private Consumer<String> myCommandAccess;
    private Language myLanguage;

    //TODO: Get the commandLine dependency out of this class
    public ClearButton(TextArea commandLine){
        this.setText(CLEAR_BUTTON_NAME);
        myLanguage = Language.ENGLISH;
        this.setOnAction(event -> {
            commandLine.setText("");
            runCommand(CLEAR_SCREEN);
        });
    }

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myCommandAccess = commandAccess;
    }

    @Override
    public void runCommand(String command) {
        myCommandAccess.accept(myLanguage.getTranslatedWord(command));
    }

    @Override
    public void setLanguage(Language newLanguage) {
        setText(newLanguage.getTranslatedWord(CLEAR_BUTTON_NAME));
    }
}
