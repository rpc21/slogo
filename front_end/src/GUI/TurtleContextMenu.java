package GUI;

import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class TurtleContextMenu extends Menu implements LanguageChangeable{

    private static final String TURTLE_CONTEXT_NAME = "Interactive Turtle Commands";
    private static final String FORWARD = "Forward";
    private static final String BACKWARD = "Backward";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";
    private static final List<String> TURTLE_COMMANDS = List.of(FORWARD, BACKWARD, RIGHT, LEFT);

    private Language myLanguage;
    private Consumer<String> myCommandAccess;

    public TurtleContextMenu(Language language, Consumer<String> commandAccess) {
        setText(TURTLE_CONTEXT_NAME);
        myLanguage = language;
        myCommandAccess = commandAccess;
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        for (String command : TURTLE_COMMANDS) {
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> {
                promptForFurtherInput(command);
            });
            this.getItems().add(item);
        }
    }

    private void promptForFurtherInput(String text) {
        IntegerCommandInputDialog dialog = new IntegerCommandInputDialog();
        Optional<String> input = dialog.showAndWait();
        input.ifPresent(argument -> myCommandAccess.accept(myLanguage.getTranslatedWord(text) + " " + argument));
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        for (int i = 0; i<getItems().size(); i++){
            getItems().get(i).setText(myLanguage.getTranslatedWord(TURTLE_COMMANDS.get(i)));
        }
    }
}
