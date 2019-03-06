package turtle;

import apis.ImmutableVisualCommand;
import nodes.*;

import java.lang.reflect.Method;

public class Turtle {

    private double myXCoor;
    private double myYCoor;
    private double myHeading;
    private double myVisibility;
    private double myPenState; // 1 means pen is down
    private double myPenColor;
    private double myPenSize;
    private double myShape;
    private double myID;
    private boolean isActive;

    public Turtle(int id){
        myXCoor = myYCoor = myHeading = myPenColor = myShape =  0;
        myPenSize = 2;
        myID = id;
        myVisibility =  myPenState = 1;
        if (id == 0)
            isActive = true;
        else
            isActive = false;

    }

    public ImmutableVisualCommand turtleAction(String actionName,  Object[] myParams){
        System.out.println("hi");
        try {

            Class<?>[] typeOfParams = new Class<?>[myParams.length];
            for (int i = 0; i < myParams.length; i++)
                typeOfParams[i] = myParams[i].getClass();
            Method m = getClass().getDeclaredMethod(actionName,typeOfParams);
            Object ret = m.invoke(this, typeOfParams, myParams);
            ImmutableVisualCommand v = (ImmutableVisualCommand)ret;
            return v;
        }
        catch (Exception e){
            System.out.println("Invalid Turtle Action");
        }
        return new VisualTurtleTurn(0,10);
    }

    public double getXCoor(){ return myXCoor; }
    public double getYCoor() { return myYCoor; }
    public double getHeading(){ return myHeading; }
    public int getPenColor(){ return (int)myPenColor;}
    public int getShape(){ return (int)myShape; }
    public int getID(){return (int)myID;}
    public int getVisibility(){ return (int)myVisibility; }
    public double getPenState(){
        return myPenState;
    }
    public boolean isActive(){ return isActive;}

    public ImmutableVisualCommand setPenSize(double pixels){
        myPenSize = pixels;
        return new VisualPenSize((int)myID,pixels);
    }
    public void setActive(boolean a ){isActive = a;}

    public ImmutableVisualCommand setShape(Double index){
        myShape = index;
        return new VisualTurtleShape((int)myID,(int)(double)index);
    }
    public ImmutableVisualCommand setPenColor(Double index){
        myPenColor = index;
        return new VisualPenColor((int)myID,(int)(double)index);
    }
    public ImmutableVisualCommand moveTurtle(Double pixels) {
        double orientation = Math.toRadians(myHeading);
        double deltaX = pixels * Math.sin(orientation);
        double deltaY = 1.0 *  pixels * Math.cos(orientation);
        myXCoor = myXCoor + deltaX;
        myYCoor = myYCoor + deltaY;
        return new VisualTurtlePosition((int)myID , myXCoor, -1.0 * myYCoor);
    }

    public void setXCoor(double x){ myXCoor = x; }
    public void setYCoor(double y){
        myYCoor = y;
    }
    public ImmutableVisualCommand turn(double degrees){
        myHeading += degrees;
        checkHeading();
        return new VisualTurtleTurn((int)myID, degrees);
    }
    public void setHeading(Double degrees){
        myHeading = degrees;
        checkHeading();
    }
    public ImmutableVisualCommand setVisibility(Double isVisible){
        myVisibility = isVisible;
        if (isVisible == 0.0)
            return new VisualHideTurtle((int)myID);
        else
            return new VisualShowTurtle((int)myID);
    }
    public ImmutableVisualCommand setPen(Double isDown){
        myPenState = isDown;
        if (isDown == 0.0)
            return new VisualPenUp((int)myID);
        else
            return new VisualPenDown((int)myID);
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
