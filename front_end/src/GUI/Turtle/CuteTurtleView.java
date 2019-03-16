package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of DisplayView that uses the cute turtle image
 */
public class CuteTurtleView extends DisplayView {
    private static final String CUTE_TURTLE_IMAGE = "cute-turtle.jpg";

    public CuteTurtleView() {
        super(new Image(CUTE_TURTLE_IMAGE));
    }

    public CuteTurtleView(Canvas turtleCanvas) {
        super(new Image(CUTE_TURTLE_IMAGE), turtleCanvas);
    }

    public CuteTurtleView(DisplayView displayView){
        super(displayView, new Image(CUTE_TURTLE_IMAGE));
    }
}
