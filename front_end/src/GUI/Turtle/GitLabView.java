package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of the DisplayView that uses the gitlab icon as the icon
 */
public class GitLabView extends DisplayView {

    private static final String GITLAB_TURTLE_PNG = "gitlab-turtle.png";

    /**
     * GitLabView Constructor
     */
    public GitLabView() {
        super(new Image(GITLAB_TURTLE_PNG));
    }

    /**
     * GitLabView Constructor
     * @param turtleCanvas canvas for turtle to draw on
     */
    public GitLabView(Canvas turtleCanvas) {
        super(new Image(GITLAB_TURTLE_PNG), turtleCanvas);
    }

    /**
     * GitLabView Constructor
     * @param displayView DisplayView to be copied
     */
    public GitLabView(DisplayView displayView){
        super(displayView, new Image(GITLAB_TURTLE_PNG));
    }
}