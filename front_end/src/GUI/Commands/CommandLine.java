package GUI.Commands;

import javafx.scene.control.TextArea;

/**
 * The CommandLine is a TextArea where the user can enter the commands for the turtles.  This class is a wrapper that
 * establishes the TextArea to have the correct preferences without having to set the preferences in the GUIDisplay
 * class
 */
public class CommandLine extends TextArea {

    private static final int ROWS = 4;
    private static final int COLUMNS = 10;

    public CommandLine(){
        super();
        setPrefRowCount(ROWS);
        setPrefColumnCount(COLUMNS);
        setWrapText(true);
    }
}
