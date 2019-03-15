package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Tabs.PaletteTabExplorer;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;
import java.util.function.Function;

public class ColorChooser extends ColorPicker implements LanguageChangeable, CommandExecutable {

    private Language myLanguage;
    private String myCommandTemplate;
    private Consumer<String> myCommandAccess;

    public ColorChooser(){
        super();
    }

    public ColorChooser(Function<Rectangle, Integer> colorPaletteAccess, String commandTemplate){
        super();
        myLanguage = Language.ENGLISH;
        myCommandTemplate = commandTemplate;
        setOnAction(event -> {
            int index = colorPaletteAccess.apply(new Rectangle(PaletteTabExplorer.COLOR_PALETTE_WIDTH,
                    PaletteTabExplorer.COLOR_PALETTE_HEIGHT, getValue()));
            runCommand(myLanguage.getTranslatedWord(myCommandTemplate) + " " + index);
        });
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
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
}
