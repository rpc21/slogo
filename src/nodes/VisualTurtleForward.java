package nodes;
import apis.CanvasAPI;

public class VisualTurtleForward extends VisualCommand {
    private double myPixels;
    public VisualTurtleForward(double pixels){
        myPixels = pixels;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleMove(-1.0 * myPixels);
    }
}
