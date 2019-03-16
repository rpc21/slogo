package GUI.Commands;

import javafx.scene.control.TextInputDialog;

/**
 * The IntegerCommandInputDialog is the a pop-up text input dialog that allows the user to enter an integer argument
 * for the command they are trying to run
 */
public class IntegerCommandInputDialog extends TextInputDialog {

    public IntegerCommandInputDialog(){
        setTitle("Argument Input Dialog");
        setContentText("Please enter the argument for your command:");
    }

}
