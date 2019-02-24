package turtle;

public class TurtleState {
    private boolean showTurtle = true;
    private double xCoor;
    private double yCoor;


    public void updateXCoor(double x){
        xCoor =x;
    }
    public void updateYCoor(double y){
        yCoor = y;
    }
    public void hideTurtle(){
        showTurtle = false;
    }
    public void showTurtle(){
        showTurtle = true;
    }
}
