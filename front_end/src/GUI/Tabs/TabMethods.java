package GUI.Tabs;

import GUI.GUI.GUIdata;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

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
        dialog.setTitle("MEthod Editor");
        dialog.setHeaderText(contents.getText().substring(1));
        dialog.setContentText("Enasdasdfasdfasdfase:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            super.runCommand(contents, value);
        });
    }
}
