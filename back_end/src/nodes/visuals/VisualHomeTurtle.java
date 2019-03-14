package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualHomeTurtle extends VisualCommand {
    private int myID;
    public VisualHomeTurtle(int id){
        myID = id;
    }
    /**
     * Invokes front end to set the position of the turtle associated with myID home and set its orientation upright
     * @see VisualUpdateAPI
     */
    public void execute(VisualUpdateAPI myCanvas){
        myCanvas.setLocation(myID, 0, 0);
        myCanvas.setOrientation(myID, 0);
    }
}
