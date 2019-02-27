package nodes;
import apis.CanvasAPI;

public class VisualClearScreen extends VisualCommand {
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.clearScreen();
    }
}