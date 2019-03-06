package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SLogoTabVariables extends SLogoTab {

    public SLogoTabVariables() {
        super();
    }

    public SLogoTabVariables(String tabTitle, GUIdata data) {
        super(tabTitle, data);
    }

    @Override
    public void addContents(String newContents) {
        String[] nameAndValue = newContents.split(" ");
        isItAlreadyThere(nameAndValue[0]);
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            //System.out.println("variablestab");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Variable Editor");
            dialog.setHeaderText(contents.getText().substring(1));
            dialog.setContentText("Enter new value:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(value -> {
                // String s = contents.getText() + " " + value;
                String[] s = contents.getText().split(" ");
                String str = "make " + s[0] + " " + value;
                System.out.println(str);
                guiData.setMyCommandToRun(str);
            });
        });
        myVBoxOfStrings.getChildren().add(0, contents);
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

}
