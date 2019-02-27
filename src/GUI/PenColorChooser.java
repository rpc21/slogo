package GUI;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class PenColorChooser extends ColorPicker implements GUIComponent {

    public PenColorChooser(){
        super();
    }

    public PenColorChooser(Consumer<Paint> penColorAccess){
        super();
        setOnAction(event -> penColorAccess.accept(getValue()));
    }
}
