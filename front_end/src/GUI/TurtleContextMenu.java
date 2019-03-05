package GUI;

import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.function.Consumer;

public class TurtleContextMenu extends ContextMenu implements LanguageChangeable{

    private static final String FORWARD = "Forward";
    private static final String BACKWARD = "Backward";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";
    private static final List<String> TURTLE_COMMANDS = List.of(FORWARD, BACKWARD, RIGHT, LEFT);

    private Language myLanguage;
    private Consumer<String> myCommandAccess;

    public TurtleContextMenu(Language language, Consumer<String> commandAccess) {
        myLanguage = language;
        myCommandAccess = commandAccess;
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        for (String command : TURTLE_COMMANDS) {
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> {
                myCommandAccess.accept(myLanguage.getTranslatedWord(command) + " 50");
            });
            this.getItems().add(item);
        }
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myLanguage = newLanguage;
        for (int i = 0; i<getItems().size(); i++){
            getItems().get(i).setText(myLanguage.getTranslatedWord(TURTLE_COMMANDS.get(i)));
        }
    }
}
