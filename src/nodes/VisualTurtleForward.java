package nodes;

import GUI.StackedCanvasPane;
import apis.CanvasAPI;
import nodes.VisualCommand;

public class VisualTurtleForward extends VisualCommand {
    private double myPixels;
    public VisualTurtleForward(double pixels){
        myPixels = pixels;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleMove(myPixels);
    }
}
