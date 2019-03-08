package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SLogoTabInteractive extends SLogoTab {

    public SLogoTabInteractive() {
        super();
    }

    public SLogoTabInteractive(String tabTitle, GUIdata data) {
        super(tabTitle, data);
    }

    @Override
    public void addContents(String newContents) {
        String[] nameAndValue = newContents.split(" ");
        isItAlreadyThere(nameAndValue[0]);
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            displayDialogMenu(contents);
        });
        myVBoxOfStrings.getChildren().add(0, contents);
    }

    public void displayDialogMenu(Label contents) {
                //        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Variable Editor");
//        dialog.setHeaderText(contents.getText().substring(1));
//        dialog.setContentText("Enter new value:");
//        Optional<String> result = dialog.showAndWait();
//        result.ifPresent(value -> {
//            runCommand(contents, value);
//        });
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
