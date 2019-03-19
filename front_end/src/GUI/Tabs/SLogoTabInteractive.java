package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * Tab that the user can click on to edit variables and methods stored in that tab
 * Abstract because implementation is different depending on if it is variables or methods
 * Author: Louis Jensen
 */
public abstract class SLogoTabInteractive extends SLogoTab {
    private String myTitle;
    private String myContents;

    /**
     * Default constructor calls superclass constructor
     */
    public SLogoTabInteractive() {
        super();
    }

    /**
     * Constructor for Interactive Tabs
     * @param tabTitle Title of Tab
     * @param data Allows info to be passed to Display
     * @param title Title of editor box
     * @param contents Contents of editor box
     */
    public SLogoTabInteractive(String tabTitle, GUIdata data, String title, String contents) {
        super(tabTitle, data);
        myTitle = title;
        myContents = contents;
    }

    /**
     * Adds item to tab display
     * @param newContents String to add to tab display
     */
    @Override
    public void addContents(String newContents) {
        String[] nameAndValue = newContents.split(" ");
        isItAlreadyThere(nameAndValue[0]);
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            displayDialogMenu(contents, myTitle, myContents);
        });
        myVBoxOfStrings.getChildren().add(0, contents);
    }

    private Dialog displayDialogMenu(Label contents, String title, String contentText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(contents.getText());
        dialog.setTitle(title);
        dialog.setContentText(contentText);
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
           runCommand(convertResultToRunnableString(contents, value));
        });
        return dialog;
    }

    protected String convertResultToRunnableString(Label label, String value){
        return null;
    }

    private void isItAlreadyThere(String stringToAdd) {
        Label labelToRemove = new Label();
        for (Node node : myVBoxOfStrings.getChildren()) {
            Label label = (Label) node;
            String[] nameAndValue = label.getText().split(" ");
            if (nameAndValue[0].equals(stringToAdd)){
                labelToRemove = label;
            }
        }
        myVBoxOfStrings.getChildren().remove(labelToRemove);
    }

    protected void runCommand(String command) {
        guiData.setMyCommandToRun(command);
    }
}
