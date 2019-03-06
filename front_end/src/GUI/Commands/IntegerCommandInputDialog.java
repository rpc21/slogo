package GUI.Commands;

import javafx.scene.control.TextInputDialog;

public class IntegerCommandInputDialog extends TextInputDialog {

    public IntegerCommandInputDialog(){
        setTitle("Argument Input Dialog");
        setContentText("Please enter the argument for your command:");
    }

}
