package GUI.Buttons;

import GUI.Commands.CommandExecutable;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Tabs.PaletteTabExplorer;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class that creates button that gives the user the ability to change the background color of the canvas or the pen
 * color of all the turtles by adding the color to the palette of available colors and running the command through
 * the back end
 */
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

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
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
