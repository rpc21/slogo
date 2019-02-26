package nodes;

public class VisualTurtleRight  {
    private double myPixels;
    public VisualTurtleRight(double pixels){
        myPixels = pixels;
    }
    public void execute(StackedCanvasPaneTurtle myCanvas) {
        myCanvas.turnLeft(myPixels);
    }
}