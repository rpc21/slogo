package nodes.visuals;
import apis.VisualUpdateAPI;

public class VisualShowTurtle extends VisualCommand {
    private int myID;
    public VisualShowTurtle(int id){
        myID = id;
    }
    /**
     * Invokes front end to show the displayed turtle associated with myID
     * @see VisualUpdateAPI
     */
    public void execute(VisualUpdateAPI myCanvas){
        myCanvas.showTurtle(myID);
    }
}