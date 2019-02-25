package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class AdvancedTurtleView extends DisplayView {

    public static final String ADVANCED_TURTLE_IMAGE = "turtle.jpg";

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
