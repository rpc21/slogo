package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of DisplayView using the green cool turtle image
 */
public class AdvancedTurtleView extends DisplayView {

    private static final String ADVANCED_TURTLE_IMAGE = "turtle.jpg";

    /**
     * AdvancedTurtleView constructor
     */
    public AdvancedTurtleView() {
        super(new Image(ADVANCED_TURTLE_IMAGE));
    }

    /**
     * AdvancedTurtleView constructor
     * @param turtleCanvas canvas for the turtle to draw on
     */
    public AdvancedTurtleView(Canvas turtleCanvas) {
        super(new Image(ADVANCED_TURTLE_IMAGE), turtleCanvas);
    }

    /**
     * AdvancedTurtleView constructor
     * @param displayView DisplayView to be copied
     */
    public AdvancedTurtleView(DisplayView displayView){
        super(displayView, new Image(ADVANCED_TURTLE_IMAGE));
    }
}

