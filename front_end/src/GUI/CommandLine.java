package GUI;

import javafx.scene.control.TextArea;

public class CommandLine extends TextArea implements GUIComponent {

    public CommandLine(){
        super();
        setPrefRowCount(4);
        setPrefColumnCount(10);
        setWrapText(true);
    }
}
