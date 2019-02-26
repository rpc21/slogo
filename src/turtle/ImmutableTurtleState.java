package turtle;

public class ImmutableTurtleState {
    private boolean showTurtle;
    private double xCoor;
    private double yCoor;

    public ImmutableTurtleState(){
        showTurtle = true;
        xCoor = 0;
        yCoor = 0;
    }

    public void getXCoor(double x){
        xCoor =x;
    }
    public void getYCoor(double y){
        yCoor = y;
    }
    public void hideTurtle(){
        showTurtle = false;
    }
    public void showTurtle(){
        showTurtle = true;
    }
}
