package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualTurtleHeading extends VisualCommand {
    private final int myID;
    private final double myDegrees;
    public VisualTurtleHeading(int id, double degrees){
        myID = id;
        myDegrees = degrees;
    }
    /**
     * Invokes front end to set the heading of turtle myID to myDegrees
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setOrientation(myID,myDegrees);
    }
}
