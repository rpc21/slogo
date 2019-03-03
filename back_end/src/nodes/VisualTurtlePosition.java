package nodes;

import apis.VisualUpdateAPI;

public class VisualTurtlePosition extends VisualCommand {
    private int myID;
    private double myX;
    private double myY;
    public VisualTurtlePosition(int id, double x, double y){
        myID = id;
        myX= x;
        myY = y;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.setLocation(myID,myX, myY);
    }
}
