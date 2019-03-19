package GUI.Pen;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Turtle.SubContextMenu;
import java.util.List;
import java.util.function.Consumer;

/**
 * The PenContextMenu is a subclass of the SubContextMenu abstract class.  The PenContextMenu allows the user to run
 * pen-specific commands by just clicking on the turtle and selecting a pen command to run.
 * Author: Ryan Culhane
 */
public class PenContextMenu extends SubContextMenu implements LanguageChangeable {

    private static final String PEN_CONTEXT_MENU = "Interactive Pen Commands";
    private static final String PEN_UP = "PenUp";
    private static final String PEN_DOWN = "PenDown";
    private static final String SET_PEN_COLOR = "SetPenColor";
    private static final String SET_PEN_WIDTH = "SetPenSize";

    /**
     * PenContextMenu constructor
     * @param language language for the context menu
     * @param commandAccess ability to run commands
     */
    public PenContextMenu(Language language, Consumer<String> commandAccess) {
        super(language, commandAccess);
        setText(PEN_CONTEXT_MENU);
    }

    @Override
    protected void defineContextSpecificCommands() {
        contextSpecificCommandsWithInput = List.of(SET_PEN_COLOR, SET_PEN_WIDTH);
        noInputContextSpecificCommands = List.of(PEN_UP, PEN_DOWN);
        allContextSpecificCommands.addAll(contextSpecificCommandsWithInput);
        allContextSpecificCommands.addAll(noInputContextSpecificCommands);
    }

}
