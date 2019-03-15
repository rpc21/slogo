package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualPenDown extends VisualCommand {
    private final int myID;
    public VisualPenDown(int id){
        myID = id;
    }
    /**
     * Invokes front end to set the pen associated with turtle myID down
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenDown(myID);
    }
}