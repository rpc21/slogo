package nodes;
import apis.CanvasAPI;

public class VisualTurtleTowards extends VisualCommand {
    private double myDegrees;
    public VisualTurtleTowards(double degrees) {
        myDegrees = degrees;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setTowards(myDegrees);
    }
}
