package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class TabMethods extends SLogoTabInteractive {

    public TabMethods() {
        super();
    }

    public TabMethods(String tabTitle, GUIdata data) {
        super(tabTitle, data);
    }

    @Override
    public void displayDialogMenu(Label contents) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Method Parameters");
        dialog.setHeaderText(contents.getText());
        dialog.setContentText("Parameters Separated by Commas:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            super.runCommand(convertResultToRunnableString(contents, value));
        });
    }

    private String convertResultToRunnableString(Label label, String value){
        String[] nameAndContents = label.getText().split(" ");
        String stringToRun = nameAndContents[0].substring(0,nameAndContents[0].length()-1) + " [ " + commaSeparatedToSpaces(value) + " ]";
        return stringToRun;
    }

    private String commaSeparatedToSpaces(String withCommas){
        String[] noCommas = withCommas.split(",");
        String withSpaces = "";
        for (String value : noCommas){
            value.trim();
            withSpaces += value + " ";
        }
        return withSpaces;
    }
}
