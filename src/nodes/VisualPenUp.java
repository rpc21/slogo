package nodes;
import apis.CanvasAPI;

public class VisualPenUp extends VisualCommand {
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setPenUp();
    }
}
