package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of DisplayView that uses the default turtle image
 * Author: Ryan Culhane
 */
public class BasicTurtleView extends DisplayView {

    private static final String TURTLE_IMAGE = "basic_turtle1.jpg";

    /**
     * BasicTurtleView construct
     */
    public BasicTurtleView() {
        super(new Image(TURTLE_IMAGE));
    }

    /**
     * BasicTurtleView construct
     * @param turtleCanvas canvas for the turtle to draw on
     */
    public BasicTurtleView(Canvas turtleCanvas) {
        super(new Image(TURTLE_IMAGE), turtleCanvas);
    }

    /**
     * BasicTurtleView construct
     * @param displayView DisplayView to be copied
     */
    public BasicTurtleView(DisplayView displayView){
        super(displayView, new Image(TURTLE_IMAGE));
    }
}
