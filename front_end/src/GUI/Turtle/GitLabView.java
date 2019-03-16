package GUI.Turtle;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Subclass of the DisplayView that uses the gitlab icon as the icon
 */
public class GitLabView extends DisplayView {

    private static final String GITLAB_TURTLE_PNG = "gitlab-turtle.png";

    public GitLabView() {
        super(new Image(GITLAB_TURTLE_PNG));
    }

    public GitLabView(Canvas turtleCanvas) {
        super(new Image(GITLAB_TURTLE_PNG), turtleCanvas);
    }

    public GitLabView(DisplayView displayView){
        super(displayView, new Image(GITLAB_TURTLE_PNG));
    }
}