package nodes;

import GUI.StackedCanvasPane;

public class VisualTurtleBackward extends VisualCommand {
    private double myPixels;
    public VisualTurtleBackward(double pixels){
        myPixels = pixels;
    }
    @Override
    public void execute(StackedCanvasPane myCanvas) {
        myCanvas.turtleMove(myPixels);
    }
}
