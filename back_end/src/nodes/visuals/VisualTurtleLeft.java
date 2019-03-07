package nodes.visuals;

import apis.VisualUpdateAPI;
import nodes.visuals.VisualCommand;

public class VisualTurtleLeft extends VisualCommand {//extends VisualCommand {
    private double myPixels;
    private int myID;
    public VisualTurtleLeft(int id,double pixels){
        myID = id;
        myPixels = pixels;
    }
    //@Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.turtleTurn(myID,myPixels);
    }
}
