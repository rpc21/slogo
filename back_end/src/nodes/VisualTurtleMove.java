package nodes;
import apis.VisualUpdateAPI;

public class VisualTurtleMove extends VisualCommand {
    private int myID;
    private double myXDelta;
    private double myYDelta;
    public VisualTurtleMove(int turtleID, double pixels, double currHeading){
        myID = turtleID;
        double orientation = Math.toRadians(currHeading);
        double deltaX = pixels * Math.sin(orientation);
        double deltaY = 1.0 *  pixels * Math.cos(orientation);
        myXDelta = deltaX;
        myYDelta = deltaY;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleMove(myID, myXDelta, -1.0 * myYDelta);
    }

    public double getXDelta(){
        return myXDelta;
    }
    public double getYDelta(){
        return myYDelta;
    }
}
