package nodes;

import apis.CanvasAPI;

public class VisualTurtlePosition extends VisualCommand {
    private double myX;
    private double myY;
    public VisualTurtlePosition(double x, double y){

        myX= x;
        myY = y;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setLocation(myX, myY);
    }
}
