package GUI.Buttons;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class HelpButton extends Button {
    private ResourceBundle myResources;

    public HelpButton(String text, ResourceBundle resources, Consumer<Void> consumer){
        this.setText(text);
        myResources = resources;
        this.setOnAction(event ->{
            consumer.accept(null);
        });
    }

}
