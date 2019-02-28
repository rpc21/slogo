package nodes;

import GUI.StackedCanvasPane;
import apis.CanvasAPI;

public class VisualTurtleRight extends VisualCommand  {
    private double myPixels;
    public VisualTurtleRight(double pixels){
        myPixels = pixels;
    }
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turnRight(myPixels);
    }
}