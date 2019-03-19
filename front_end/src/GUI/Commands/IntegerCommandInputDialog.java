package GUI.Commands;

import javafx.scene.control.TextInputDialog;

/**
 * The IntegerCommandInputDialog is the a pop-up text input dialog that allows the user to enter an integer argument
 * for the command they are trying to run
 * Author: Ryan Culhane
 */
public class IntegerCommandInputDialog extends TextInputDialog {

    private static final String TITLE = "Argument Input Dialog";
    private static final String CONTENT_TEXT = "Please enter the argument for your command:";

    /**
     * Constructor for the IntegerCommandInputDialog
     */
    public IntegerCommandInputDialog(){
        setTitle(TITLE);
        setContentText(CONTENT_TEXT);
    }

}
