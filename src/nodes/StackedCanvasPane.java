package nodes;

public class StackedCanvasPane {

    private boolean penDown;
    private double turtlePosition;
    private double orientation;

    public StackedCanvasPane(){
        super();
        turtlePosition = 0;
        penDown = true;
    }

    public void turtleMove(double pixels){
        turtlePosition += pixels;
    }

    public void turnRight(double degrees){
        orientation += degrees;
    }
    public void turnLeft(double degrees){
        orientation -= degrees;
    }

    public void setPenUp(){
        penDown = false;
    }

    public void setPenDown(){
        penDown = true;
    }
}
