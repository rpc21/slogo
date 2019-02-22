package GUI;

import javafx.scene.image.Image;

public class TurtleView extends DisplayView {

    public static final String TURTLE_IMAGE = "turtle.jpg";

    public TurtleView(){
        super(new Image(TURTLE_IMAGE));
        setFitHeight(25);
        setFitWidth(20);
    }

}
