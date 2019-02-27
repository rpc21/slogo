package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ClearButton extends Button {
    public ClearButton(String text, TextArea commandLine){
        this.setText(text);
        this.setOnAction(event -> {
            commandLine.setText("");
        });
    }
}
