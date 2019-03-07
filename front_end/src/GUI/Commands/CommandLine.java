package GUI.Commands;

import GUI.GUI.GUIComponent;
import javafx.scene.control.TextArea;

public class CommandLine extends TextArea implements GUIComponent {

    private static final int ROWS = 4;
    private static final int COLUMNS = 10;
    
    public CommandLine(){
        super();
        setPrefRowCount(ROWS);
        setPrefColumnCount(COLUMNS);
        setWrapText(true);
    }
}
