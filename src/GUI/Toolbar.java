package GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class Toolbar extends HBox implements GUIComponent {

    public Toolbar(){
        super();
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);

    }
}
