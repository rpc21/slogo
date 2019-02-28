package nodes;

import apis.CanvasAPI;

public class VisualTurtleTurn extends VisualCommand  {
    private double myDegrees;
    public VisualTurtleTurn(double degrees){
        myDegrees = degrees;
    }
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleTurn(myDegrees);
    }
}