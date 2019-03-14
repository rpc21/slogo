package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualTurtleTurn extends VisualCommand {
    private int myID;
    private double myDegrees;
    public VisualTurtleTurn(int id,double degrees){
        myID = id;
        myDegrees = degrees;
    }
    /**
     * Invokes front end to turn turtle myID's heading by myDegrees
     * @see VisualUpdateAPI
     */
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleTurn(myID,myDegrees);
    }
}