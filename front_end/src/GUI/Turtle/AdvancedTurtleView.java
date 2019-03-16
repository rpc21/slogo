package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of DisplayView using the green cool turtle image
 */
public class AdvancedTurtleView extends DisplayView {

    private static final String ADVANCED_TURTLE_IMAGE = "turtle.jpg";

    public AdvancedTurtleView() {
        super(new Image(ADVANCED_TURTLE_IMAGE));
    }

    public AdvancedTurtleView(Canvas turtleCanvas) {
        super(new Image(ADVANCED_TURTLE_IMAGE), turtleCanvas);
    }

    public AdvancedTurtleView(DisplayView displayView){
        super(displayView, new Image(ADVANCED_TURTLE_IMAGE));
    }
}

