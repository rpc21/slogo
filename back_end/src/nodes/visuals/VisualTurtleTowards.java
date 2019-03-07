package nodes.visuals;
import apis.VisualUpdateAPI;
import nodes.visuals.VisualCommand;

public class VisualTurtleTowards extends VisualCommand {
    private int myID;
    private double myDegrees;
    public VisualTurtleTowards(int id,double degrees) {
        myID = id;
        myDegrees = degrees;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setTowards(myID,myDegrees);
    }
}
