package GUI;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.function.Consumer;

public class PenContextMenu extends Menu implements LanguageChangeable {

    private static final String PEN_CONTEXT_MENU = "Interactive Pen Commands";
    private static final String PEN_UP = "PenUp";
    private static final String PEN_DOWN = "PenDown";
    private static final String SET_PEN_COLOR = "SetPenColor";
    private static final String SET_PEN_WIDTH = "SetPenSize";
    private static final List<String> PEN_COMMANDS = List.of(PEN_UP, PEN_DOWN, SET_PEN_COLOR, SET_PEN_WIDTH);

    private Language myLanguage;
    private Consumer<String> myCommandAccess;

    public PenContextMenu(Language language, Consumer<String> commandAccess) {
        setText(PEN_CONTEXT_MENU);
        myLanguage = language;
        myCommandAccess = commandAccess;
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        for (String command : PEN_COMMANDS) {
            MenuItem item = new MenuItem(myLanguage.getTranslatedWord(command));
            item.setOnAction(e -> {
                myCommandAccess.accept(myLanguage.getTranslatedWord(command));
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
    }
}
