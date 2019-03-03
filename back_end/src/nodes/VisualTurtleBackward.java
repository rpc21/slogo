package nodes;
import apis.VisualUpdateAPI;

public class VisualTurtleBackward extends VisualCommand {
    private int myID;
    private double myXDelta;
    private double myYDelta;
    public VisualTurtleBackward(int id, double x, double y){
        myID = id;
        myXDelta = x;
        myYDelta = y;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleMove(myID, myXDelta, myYDelta);
    }
}