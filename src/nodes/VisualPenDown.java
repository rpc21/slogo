package nodes;
import apis.CanvasAPI;

public class VisualPenDown extends VisualCommand {
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setPenDown();
    }
}
