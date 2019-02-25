public class TurtleMove extends VisualCommand {
    private double myDistance;
    public TurtleMove(double length){
        myDistance = length;
    }
    public void execute(Canvas myCanvas){
        myCanvas.turtleMove(myDistance);
    }
}
