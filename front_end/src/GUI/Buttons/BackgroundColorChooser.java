package GUI.Buttons;

import GUI.GUI.GUIComponent;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class BackgroundColorChooser extends ColorPicker implements GUIComponent {

    public BackgroundColorChooser(){
        super();
    }

    public BackgroundColorChooser(Consumer<Paint> backgroundColorAccess){
        super();
        setOnAction(event -> backgroundColorAccess.accept(getValue()));
    }
}
