package nodes.visuals;
import apis.VisualUpdateAPI;

public class VisualHideTurtle extends VisualCommand {
    private int myID;
    public VisualHideTurtle(int id){
        myID = id;
    }
    public void execute(VisualUpdateAPI myCanvas){
        myCanvas.hideTurtle(myID);
    }
}
