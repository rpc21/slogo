package nodes.visuals;
import apis.VisualUpdateAPI;

public class VisualHomeTurtle extends VisualCommand {
    private int myID;
    public VisualHomeTurtle(int id){
        myID = id;
    }
    public void execute(VisualUpdateAPI myCanvas){
        myCanvas.setLocation(myID, 0, 0);
        myCanvas.setOrientation(myID, 0);
    }
}
