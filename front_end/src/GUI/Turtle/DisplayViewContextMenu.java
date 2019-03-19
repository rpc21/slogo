package GUI.Turtle;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Pen.PenContextMenu;
import javafx.scene.control.ContextMenu;
import java.util.function.Consumer;

/**
 * Context menu that contains the TurtleContextMenu and PenContextMenus as sub menus
 * Author: Ryan Culhane
 */
public class DisplayViewContextMenu extends ContextMenu implements LanguageChangeable {

    private TurtleContextMenu myTurtleContextMenu;
    private PenContextMenu myPenContextMenu;

    /**
     * DisplayViewContextMenu constructor
     * @param language language of the program
     * @param commandAccess ability to run commands
     */
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
