package turtle;

public class Turtle {
    private double myXCoor;
    private double myYCoor;
    private double myHeading;
    private boolean myVisibility;
    private boolean myPenIsUp;
    private int myPenColor;
    private int myShape;
    private int myID;
    private boolean isActive;
    public Turtle(int id){
        myXCoor = myYCoor = myHeading = myPenColor = myShape = 0;
        myID = id;
        myVisibility = true;
        myPenIsUp = true;
        if (id == 0)
            isActive = true;
        else
            isActive = false;

    }

    public double getXCoor(){ return myXCoor; }
    public double getYCoor() { return myYCoor; }
    public double getHeading(){ return myHeading; }
    public int getPenColor(){ return myPenColor;}
    public int getShape(){ return myShape; }
    public int getID(){return myID;}
    public boolean getVisibility(){ return myVisibility; }
    public boolean getPenState(){
        return myPenIsUp;
    }
    public boolean isActive(){ return isActive;}

    public void setActive(boolean a ){isActive = a;}
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
        checkHeading();
    }
    public void setHeading(double degrees){
        myHeading = degrees;
        checkHeading();
    }
    public void setVisibility(boolean isVisible){ myVisibility = isVisible; }
    public void setPenState(boolean isPenUp){
        myPenIsUp = isPenUp;
    }

    private void checkHeading(){
        if (myHeading > 360)
            myHeading = myHeading % 360;
        if (myHeading < 0) {
            myHeading = myHeading % 360;
            myHeading = myHeading + 360;

        }
    }
}
