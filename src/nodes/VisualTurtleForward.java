package nodes;

import GUI.StackedCanvasPane;
import nodes.VisualCommand;

public class VisualTurtleForward extends VisualCommand {
    private double myPixels;
    public VisualTurtleForward(double pixels){
        myPixels = pixels;
    }
    @Override
    public void execute(StackedCanvasPane myCanvas) {
        myCanvas.turtleMove(-1.0 * myPixels);
    }
}
