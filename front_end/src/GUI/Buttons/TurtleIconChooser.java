package GUI.Buttons;

import GUI.Commands.CommandExecutable;

import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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

    @Override
    public void giveAbilityToRunCommands(Consumer<String> commandAccess) {
        myConsumer = commandAccess;
    }

    @Override
    public void runCommand(String command) {
        //do nothing, handled in super.buildChooser()
    }
}
