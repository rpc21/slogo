package turtle;

public class Turtle {
    private double myXCoor;
    private double myYCoor;
    private double myHeading;
    private boolean myVisibility;
    private boolean myPenIsUp;
    private int myPenColor;
    private int myShape;
    public Turtle(){
        myXCoor = myYCoor = myHeading = myPenColor = myShape = 0;
        myVisibility = true;
        myPenIsUp = true;
    }


    public double getXCoor(){ return myXCoor; }
    public double getYCoor() { return myYCoor; }
    public double getHeading(){ return myHeading; }
    public int getPenColor(){ return myPenColor;}
    public int getShape(){ return myShape; }
    public boolean getVisibility(){ return myVisibility; }
    public boolean getPenState(){
        return myPenIsUp;
    }

    public void setShape(int index){myShape = index;}
    public void setPenColor(int index){myPenColor = index;}
    public void updateXCoor(double x){ myXCoor = x; }
    public void updateYCoor(double y){
        myYCoor = y;
    }
    public void setXCoor(double x){ myXCoor = x; }
    public void setYCoor(double y){
        myYCoor = y;
    }
    public void updateHeading(double degrees){
        myHeading += degrees;
    }
    public void setHeading(double degrees){
        myHeading = degrees;
    }
    public void setVisibility(boolean isVisible){ myVisibility = isVisible; }
    public void setPenState(boolean isPenUp){
        myPenIsUp = isPenUp;
    }

}
