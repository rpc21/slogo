package nodes;
import apis.VisualUpdateAPI;

public class VisualTurtleForward extends VisualCommand {
    private int myID;
    private double myXDelta;
    private double myYDelta;
    public VisualTurtleForward(int id, double x, double y){
        myID = id;
        myXDelta = x;
        myYDelta = y;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleMove(myID, myXDelta, myYDelta);
    }
}
