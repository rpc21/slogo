package nodes;
import apis.CanvasAPI;

public class VisualHomeTurtle extends VisualCommand {
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setLocation(0,0);
    }
}
