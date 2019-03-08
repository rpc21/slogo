package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class TabVariables extends SLogoTabInteractive {

    public TabVariables() {
        super();
    }

    public TabVariables(String tabTitle, GUIdata data) {
        super(tabTitle, data);
    }

    @Override
    public void displayDialogMenu(Label contents) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Variable Editor");
        dialog.setHeaderText(contents.getText().substring(1));
        dialog.setContentText("Enter new value:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            super.runCommand(contents, value);
        });
    }
}
