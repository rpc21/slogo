package nodes.visuals;

import apis.VisualUpdateAPI;

public class VisualTurtleTurn extends VisualCommand {
    private int myID;
    private double myDegrees;
    public VisualTurtleTurn(int id,double degrees){
        myID = id;
        myDegrees = degrees;
    }
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleTurn(myID,myDegrees);
    }
}