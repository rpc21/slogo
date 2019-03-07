package GUI.Turtle;

import GUI.Commands.Language;
import GUI.Commands.LanguageChangeable;

import java.util.List;
import java.util.function.Consumer;

public class TurtleContextMenu extends SubContextMenu implements LanguageChangeable {

    private static final String TURTLE_CONTEXT_NAME = "Interactive Turtle Commands";
    private static final String FORWARD = "Forward";
    private static final String BACKWARD = "Backward";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";

    public TurtleContextMenu(Language language, Consumer<String> commandAccess) {
        super(language, commandAccess);
        setText(TURTLE_CONTEXT_NAME);
    }

    @Override
    protected void defineContextSpecificCommands() {
        contextSpecificCommandsWithInput = List.of(FORWARD, BACKWARD, RIGHT, LEFT);
        allContextSpecificCommands.addAll(contextSpecificCommandsWithInput);
    }

}
