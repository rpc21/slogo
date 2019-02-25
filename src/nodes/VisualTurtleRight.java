package nodes;

public class VisualTurtleRight extends VisualCommand {
    private double myPixels;
    public VisualTurtleRight(double pixels){
        myPixels = pixels;
    }
    @Override
    public void execute(StackedCanvasPane myCanvas) {
        myCanvas.turnLeft(myPixels);
    }
}