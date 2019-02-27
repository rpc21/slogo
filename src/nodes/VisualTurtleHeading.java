package nodes;
import apis.CanvasAPI;

public class VisualTurtleHeading extends VisualCommand {
    private double myDegrees;
    public VisualTurtleHeading(double degrees){
        myDegrees = degrees;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.setOrientation(myDegrees);
    }
}
