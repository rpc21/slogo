package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of DisplayView that uses the cute turtle image
 * Author: Ryan Culhane
 */
public class CuteTurtleView extends DisplayView {

    private static final String CUTE_TURTLE_IMAGE = "cute-turtle.jpg";

    /**
     * CuteTurtleView Constructor
     */
    public CuteTurtleView() {
        super(new Image(CUTE_TURTLE_IMAGE));
    }

    /**
     * CuteTurtleView Constructor
     * @param turtleCanvas canvas for the turtle to draw on
     */
    public CuteTurtleView(Canvas turtleCanvas) {
        super(new Image(CUTE_TURTLE_IMAGE), turtleCanvas);
    }

    /**
     * CuteTurtleView Constructor
     * @param displayView DisplayView to be copied
     */
    public CuteTurtleView(DisplayView displayView){
        super(displayView, new Image(CUTE_TURTLE_IMAGE));
    }
}
