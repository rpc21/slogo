package nodes;
import apis.VisualUpdateAPI;

public class VisualClearScreen extends VisualCommand {
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.clearScreen();
    }
}