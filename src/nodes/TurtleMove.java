package nodes;

import nodes.VisualCommand;

public class TurtleMove {// extends VisualCommand {
    private double myDistance;
    public TurtleMove(double length){
        myDistance = length;
    }
    public void execute(StackedCanvasPaneTurtle myCanvas){
        myCanvas.turtleMove(myDistance);
    }
}
