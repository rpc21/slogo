package GUI.Buttons;

import GUI.Commands.CommandExecutable;

import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Subclass of the PaletteChooser abstract class that takes in Strings for the consumer and the Shapes properties
 * file to define the different possible turtle icons.  Allows the user to change the shape of the turtles from a
 * drop down menu.
 */
public class TurtleIconChooser extends PaletteChooser implements CommandExecutable {

    public static final String SHAPES_BUNDLE = "Shapes";
    private static final String SET_SHAPE = "SetShape";

    public TurtleIconChooser(Consumer<String> consumer){
        super(consumer);
    }

    @Override
    protected void createBundle() {
        myBundle = ResourceBundle.getBundle(SHAPES_BUNDLE);
    }

    @Override
    protected String processKeyForConsumption(String key) {
        int index = Collections.list(myBundle.getKeys()).indexOf(key) + 1;
        return myLanguage.getTranslatedWord(SET_SHAPE) + " " + index;
    }

    /**
     * Gives the class the ability to run commands by passing a consumer that takes a String and passes
     * the command to the parser to be run through the backend and have its effects displayed on the front end as
     * well as stored in the backend
     * @param commandAccess a consumer that feeds the command to the parser to run the command through the backend.
     */
    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myConsumer = commandAccess;
    }

    /**
     * Method that calls the accept method on the consumer that was passed in the giveAbilityToRunCommands method
     * @param command the command to be run
     */
    @Override
    public void runCommand(String command) {
        //do nothing, handled in super.buildChooser()
    }
}
