package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SLogoTabInteractive extends SLogoTab {
    private String myTitle;
    private String myContents;

    public SLogoTabInteractive() {
        super();
    }

    public SLogoTabInteractive(String tabTitle, GUIdata data, String title, String contents) {
        super(tabTitle, data);
        myTitle = title;
        myContents = contents;
    }

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

    public Dialog displayDialogMenu(Label contents, String title, String contentText) {
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
        String[] nameAndValue = label.getText().split(" ");
        String stringToRun = "make " + nameAndValue[0] + " " +value;
        return stringToRun;
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
