package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of DisplayView that uses the default turtle image
 */
public class BasicTurtleView extends DisplayView {

    private static final String TURTLE_IMAGE = "basic_turtle1.jpg";

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
