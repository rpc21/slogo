package nodes;

import apis.CanvasAPI;

public class VisualTurtleRight extends VisualCommand  {
    private double myDegrees;
    public VisualTurtleRight(double degrees){
        myDegrees = degrees;
    }
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turnRight(myDegrees);
    }
}