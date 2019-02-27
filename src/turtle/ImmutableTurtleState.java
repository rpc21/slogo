package turtle;

public class ImmutableTurtleState {
    private double xCoor;
    private double yCoor;
    private double heading;
    private boolean visibility;
    private boolean penState;

    public ImmutableTurtleState(double x,double y, double h, boolean v, boolean p ){
        xCoor = x;
        yCoor = y;
        heading = h;
        visibility = v;
        penState = p;
    }

    public double getXCoor(){ return xCoor; }
    public double getYCoor(){
        return yCoor;
    }
    public double getHeading(){return heading;}
    public boolean getVisibility(){
        return visibility;
    }
    public boolean getPenState(){ return penState; }
}
