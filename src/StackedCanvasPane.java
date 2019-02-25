
public class SlogoCanvas {

    private boolean penDown;
    private double turtlePosition;
    private double orientation;

    public SlogoCanvas(){
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
