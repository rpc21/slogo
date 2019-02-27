package nodes;


import apis.CanvasAPI;

public class VisualTurtleBackward extends VisualCommand {
    private double myPixels;
    public VisualTurtleBackward(double pixels){
        myPixels = pixels;
    }
    @Override
    public void execute(CanvasAPI myCanvas) {
        myCanvas.turtleMove(myPixels);
    }
}
