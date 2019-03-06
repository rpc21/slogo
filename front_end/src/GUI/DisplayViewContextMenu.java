package GUI;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;

import java.util.function.Consumer;

public class DisplayViewContextMenu extends ContextMenu implements LanguageChangeable {

    private TurtleContextMenu myTurtleContextMenu;
    private PenContextMenu myPenContextMenu;

    public DisplayViewContextMenu(Language language, Consumer<String> commandAccess){
        myTurtleContextMenu = new TurtleContextMenu(language,commandAccess);
        myPenContextMenu = new PenContextMenu(language, commandAccess);
        getItems().add(myPenContextMenu);
        getItems().add(myTurtleContextMenu);
    }

    @Override
    public void setLanguage(Language newLanguage) {
        myTurtleContextMenu.setLanguage(newLanguage);
        myPenContextMenu.setLanguage(newLanguage);
    }
}
