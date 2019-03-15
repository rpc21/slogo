package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualPenSize extends VisualCommand {
    private final int myID;
    private final double myPenPixels;
    public VisualPenSize(int id, double p){
        myID = id;
        myPenPixels = p;
    }
    /**
     * Invokes front end to set the pen associated with turtle myID to have the size myPenPixels
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenSize(myID,myPenPixels);
    }
}