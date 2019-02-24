package GUI;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class PenColorChooser extends ColorPicker implements GUIComponent {

    public PenColorChooser(){
        super();
    }

    public PenColorChooser(Consumer<Color> penColorAccess){
        super();
        setOnAction(event -> penColorAccess.accept(getValue()));
    }
}
