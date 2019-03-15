package GUI.Turtle;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Pen.PenContextMenu;
import javafx.scene.control.ContextMenu;

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

    /**
     * Change the language dependent features of the class to accommodate the new language
     * @param newLanguage new language that the program is being changed to
     */
    @Override
    public void setLanguage(Language newLanguage) {
        myTurtleContextMenu.setLanguage(newLanguage);
        myPenContextMenu.setLanguage(newLanguage);
    }
}
