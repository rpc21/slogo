package GUI;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.function.Consumer;

public class PenContextMenu extends ContextMenu implements LanguageChangeable {

    private static final String PEN_UP = "Forward";
    private static final String PEN_DOWN = "Backward";
    private static final String SET_PEN_COLOR = "Right";
    private static final String SET_PEN_WIDTH = "Left";
    private static final List<String> PEN_COMMANDS = List.of(FORWARD, BACKWARD, RIGHT, LEFT);

    private Language myLanguage;
    private Consumer<String> myCommandAccess;

    public TurtleContextMenu(Language language, Consumer<String> commandAccess) {
        myLanguage = language;
        myCommandAccess = commandAccess;
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        for (String command : PEN_COMMANDS) {
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
            getItems().get(i).setText(myLanguage.getTranslatedWord(PEN_COMMANDS.get(i)));
        }
    }\
}
