package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualPenColor extends VisualCommand {
    private int myID;
    private int myIndex;
    public VisualPenColor(int id, int index){
        myID = id;
        myIndex = index;
    }
    /**
     * Invokes front end to set the color of the pen associated with turtle myID to the color associated with myIndex
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setPenColor(myID, myIndex);
    }
}