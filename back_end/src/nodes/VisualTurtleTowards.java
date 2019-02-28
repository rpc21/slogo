package nodes;
import apis.CanvasAPI;

public class VisualTurtleTowards extends VisualCommand {
    private double myXCoor;
    private double myYCoor;
    public VisualTurtleTowards(double x, double y) {
        myXCoor = x;
        myYCoor = y;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setTowards(myXCoor,myYCoor);
    }
}
