package nodes;
import apis.VisualUpdateAPI;

public class VisualTurtleHeading extends VisualCommand {
    private int myID;
    private double myDegrees;
    public VisualTurtleHeading(int id, double degrees){
        myID = id;
        myDegrees = degrees;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setOrientation(myID,myDegrees);
    }
}
