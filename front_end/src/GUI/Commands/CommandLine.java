package GUI.Commands;

import javafx.scene.control.TextArea;

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
