package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualTurtleShape extends VisualCommand {
    private int myID;
    private int myIndex;
    public VisualTurtleShape(int id, int index){
        myID = id;
        myIndex = index;
    }
    /**
     * Invokes front end to set the shape of turtle myID to the shape associated with myIndex
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setShape(myID,myIndex);
    }
}