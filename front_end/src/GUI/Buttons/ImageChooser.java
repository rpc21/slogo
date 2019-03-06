package GUI.Buttons;

import GUI.GUI.GUIComponent;
import javafx.scene.control.ComboBox;

import java.util.function.Consumer;

public class ImageChooser<T> extends ComboBox<T> implements GUIComponent {

    public ImageChooser(){
        super();
    }

    public ImageChooser(Consumer<T> iconConsumer){
        super();
        setOnAction(event -> iconConsumer.accept(getValue()));
    }
}
