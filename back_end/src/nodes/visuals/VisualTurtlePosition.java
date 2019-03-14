package nodes.visuals;
import apis.VisualUpdateAPI;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualTurtlePosition extends VisualCommand {
    private int myID;
    private double myX;
    private double myY;
    public VisualTurtlePosition(int id, double x, double y){
        myID = id;
        myX= x;
        myY = y;
    }
    /**
     * Invokes front end to set the position of turtle myID to myX, myY
     * @see VisualUpdateAPI
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setLocation(myID,myX, myY);
    }
}
