package GUI.Commands;

import GUI.GUI.GUIComponent;
import javafx.scene.control.TextArea;

public class CommandLine extends TextArea implements GUIComponent {

    public CommandLine(){
        super();
        setPrefRowCount(4);
        setPrefColumnCount(10);
        setWrapText(true);
    }
}
