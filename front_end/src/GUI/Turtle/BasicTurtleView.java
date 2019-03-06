package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class BasicTurtleView extends DisplayView {

    public static final String TURTLE_IMAGE = "basic_turtle1.jpg";

    public BasicTurtleView() {
        super(new Image(TURTLE_IMAGE));
    }

    public BasicTurtleView(Canvas turtleCanvas) {
        super(new Image(TURTLE_IMAGE), turtleCanvas);
    }

    public BasicTurtleView(DisplayView displayView){
        super(displayView, new Image(TURTLE_IMAGE));
    }
}
