package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualHideTurtle extends VisualCommand {
    private final int myID;
    public VisualHideTurtle(int id){
        myID = id;
    }
    /**
     * Invokes front end to hide the displayed turtle associated with myID
     * @see VisualUpdateAPI
     */
    public void execute(VisualUpdateAPI myCanvas){
        myCanvas.hideTurtle(myID);
    }
}
