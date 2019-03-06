package nodes;

import apis.VisualUpdateAPI;

public class VisualTurtleRight extends VisualCommand  {
    private int myID;
    private double myDegrees;
    public VisualTurtleRight(int id,double degrees){
        myID = id;
        myDegrees = degrees;
    }
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleTurn(myID,myDegrees);
    }
}