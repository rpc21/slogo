package GUI.Pen;

import GUI.Commands.IntegerCommandInputDialog;
import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;
import GUI.Turtle.SubContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class PenContextMenu extends SubContextMenu implements LanguageChangeable {

    private static final String PEN_CONTEXT_MENU = "Interactive Pen Commands";
    private static final String PEN_UP = "PenUp";
    private static final String PEN_DOWN = "PenDown";
    private static final String SET_PEN_COLOR = "SetPenColor";
    private static final String SET_PEN_WIDTH = "SetPenSize";

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
