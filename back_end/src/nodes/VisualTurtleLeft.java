package nodes;

import apis.CanvasAPI;

public class VisualTurtleLeft extends VisualCommand {//extends VisualCommand {
    private double myPixels;
    public VisualTurtleLeft(double pixels){
        myPixels = pixels;
    }
    //@Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleTurn(myPixels);
    }
}
