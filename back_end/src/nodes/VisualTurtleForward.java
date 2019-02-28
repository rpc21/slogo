package nodes;
import apis.CanvasAPI;

public class VisualTurtleForward extends VisualCommand {
    private double myXDelta;
    private double myYDelta;
    public VisualTurtleForward(double x, double y){
        myXDelta = x;
        myYDelta = y;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleMove(myXDelta, myYDelta);
    }
}
