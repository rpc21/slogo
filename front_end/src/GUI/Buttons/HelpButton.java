package GUI.Buttons;

import javafx.scene.control.Button;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class HelpButton extends Button {

    public HelpButton(String text, ResourceBundle resources, Consumer<Void> consumer){
        this.setText(text);
        this.setOnAction(event ->{
            consumer.accept(null);
        });
    }

}
