package nodes;
import apis.VisualUpdateAPI;

public class VisualShowTurtle extends VisualCommand {
    private int myID;
    public VisualShowTurtle(int id){
        myID = id;
    }
    public void execute(VisualUpdateAPI myCanvas){
        myCanvas.hideTurtle(myID);
    }
}