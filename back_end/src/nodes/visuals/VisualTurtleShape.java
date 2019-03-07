package nodes.visuals;

import apis.VisualUpdateAPI;
import nodes.visuals.VisualCommand;

public class VisualTurtleShape extends VisualCommand {
    private int myID;
    private int myIndex;
    public VisualTurtleShape(int id, int index){
        myID = id;
        myIndex = index;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setShape(myID,myIndex);
    }
}