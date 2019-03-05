package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SLogoTabVariables extends SLogoTab {

    public SLogoTabVariables(){
        super();
    }

    public SLogoTabVariables(String tabTitle, GUIdata data){
        super(tabTitle, data);
    }

    @Override
    public void addContents(String newContents) {
        Label contents = new Label(newContents);
        contents.setWrapText(true);
        contents.setOnMouseClicked(event -> {
            //System.out.println("variablestab");
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Variable Editor");
            dialog.setHeaderText(contents.getText());
            dialog.setContentText("Enter new value:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(value -> {
                System.out.println("Your value: " + value);
                guiData.setMyCommandToRun(contents.getText() + " " + value);
            });
        });
        myVBoxOfStrings.getChildren().add(0, contents);
    }
}
