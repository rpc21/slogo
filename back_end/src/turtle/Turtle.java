package turtle;

import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
import nodes.visuals.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Turtle implements TurtleInterface{

    private double myXCoor;
    private double myYCoor;
    private double myHeading;
    private int myVisibility;
    private int myPenState; // 1 means pen is down
    private int myPenColor;
    private double myPenSize;
    private int myShape;
    private int myID;
    private boolean isActive;

    private static final double XBOUNDARY = 400;
    private static final double YBOUNDARY = 225;
    private static final double FULL_CIRCLE = 360;
    public Turtle(int id){
        myXCoor = myYCoor = myHeading = myPenColor = 0;
        myPenSize = 2;
        myID = id;
        myVisibility = myPenState = myShape = 1;
        if (id == 0)
            isActive = true;
        else
            isActive = false;

    }

    List<ImmutableVisualCommand> turtleAction(String actionName, Object[] myParams) throws InvalidInputException {
        try {

            Class<?>[] typeOfParams = new Class<?>[myParams.length];

            for (int i = 0; i < myParams.length; i++)
                typeOfParams[i] = myParams[i].getClass();
            Method m = getClass().getDeclaredMethod(actionName,typeOfParams);
            Object ret = m.invoke(this, myParams);
            List<ImmutableVisualCommand> v = (List<ImmutableVisualCommand>)ret;
            return v;
        }
        catch (Exception e){
            throw new InvalidCommandException(actionName);
        }
    }

    public double getXCoor(){ return myXCoor; }
    public double getYCoor() { return myYCoor; }
    public double getHeading(){ return myHeading; }
    public int getPenColor(){ return myPenColor;}
    public int getShape(){
        System.out.println(myShape);
        return myShape; }
    public int getID(){return myID;}
    public int getVisibility(){ return myVisibility; }
    public double getPenState(){
        return myPenState;
    }
    boolean isActive(){ return isActive;}

    private List<ImmutableVisualCommand> setPenSize(Double pixels){
        myPenSize = pixels;
        return Arrays.asList(new VisualPenSize(myID,pixels));
    }

    void setActive(boolean a){isActive = a;}


    private List<ImmutableVisualCommand> move(Double pixels) {
        double orientation = Math.toRadians(myHeading);
        double deltaX = pixels * Math.sin(orientation);
        double deltaY = 1.0 *  pixels * Math.cos(orientation);
        if (turtleStaysInBounds(deltaX, deltaY)) {
            myXCoor = myXCoor + deltaX;
            myYCoor = myYCoor + deltaY;
            return Arrays.asList(new VisualTurtlePosition( myID, myXCoor, -1.0 * myYCoor));
        }
        else{
            return turtleOffBounds(deltaX,deltaY);
        }
    }

    private boolean turtleStaysInBounds(double deltaX, double deltaY){
        return ((Math.abs(myXCoor + deltaX) < XBOUNDARY) &&
                (Math.abs(myYCoor + deltaY) < YBOUNDARY));
    }

    private List<ImmutableVisualCommand> turtleOffBounds(double deltaX,double deltaY){
        List<ImmutableVisualCommand> myOffBoundsVisuals = new ArrayList<>();

        ImmutableVisualCommand myCurrentPenState = getPenCommand();

        double[] xCoordinates = offBoundsPoints(myXCoor,deltaX,400);
        double[] yCoordinates = offBoundsPoints(myYCoor,deltaY,215);
        myXCoor = xCoordinates[2];
        myYCoor = yCoordinates[2];

        myOffBoundsVisuals.add(new VisualTurtlePosition(myID, xCoordinates[0], -1.0 * yCoordinates[0]));
        myOffBoundsVisuals.add(new VisualPenUp(myID));
        myOffBoundsVisuals.add(new VisualTurtlePosition(myID, xCoordinates[1], -1.0 * yCoordinates[1]));
        myOffBoundsVisuals.add(myCurrentPenState);
        myOffBoundsVisuals.add(new VisualTurtlePosition(myID, xCoordinates[2], -1.0 * yCoordinates[2]));
        return myOffBoundsVisuals;
    }

    private ImmutableVisualCommand getPenCommand(){
        if (myPenState == 1.0) {
            return new VisualPenDown(myID);
        }
        else {
            return new VisualPenUp(myID);
        }
    }

    private double[] offBoundsPoints(double currentValue, double shift, double boundary){
        double[] coordinates = new double[3];
        if (Math.abs(currentValue + shift) <= boundary){
            coordinates[0] = coordinates[1] = coordinates[2] = currentValue + shift;
        }
        else {
            coordinates[0] = Math.signum(currentValue) * boundary;
            coordinates[1] = -1.0 * coordinates[0];
            double remainingDelta = Math.abs(currentValue - coordinates[0]);
            coordinates[2] = coordinates[1] + Math.signum(shift) * remainingDelta;
        }
        return coordinates;

    }

    private void setXCoor(double x){ myXCoor = x; }
    private void setYCoor(double y){
        myYCoor = y;
    }


    private List<ImmutableVisualCommand> goHome(){
        setHeading(0.0);
        setXCoor(0.0);
        setYCoor(0.0);
        return Arrays.asList(new VisualHomeTurtle(myID));
    }

    private List<ImmutableVisualCommand> setShape(Double index){
        myShape = (int)(double)index;
        return Arrays.asList(new VisualTurtleShape(myID,(int)(double)index));
    }

    private List<ImmutableVisualCommand> setPenColor(Double index){
        myPenColor = (int)(double)index;
        return Arrays.asList(new VisualPenColor(myID,(int)(double)index));
    }

    private List<ImmutableVisualCommand> setPosition(Double x, Double y){
        myXCoor = x;
        myYCoor = y;
        return Arrays.asList(new VisualTurtlePosition(myID , myXCoor, -1.0 * myYCoor));
    }
    private List<ImmutableVisualCommand> turn(Double degrees){
        myHeading += degrees;
        checkHeading();
        return Arrays.asList(new VisualTurtleTurn(myID, degrees));
    }
    private List<ImmutableVisualCommand> setHeading(Double degrees){
        myHeading = degrees;
        checkHeading();
        return Arrays.asList(new VisualTurtleHeading(myID,degrees));
    }
    private List<ImmutableVisualCommand> setVisibility(Integer isVisible){
        myVisibility = isVisible;
        if (isVisible == 0.0) {
            return Arrays.asList(new VisualHideTurtle(myID));
        }
        else {
            return Arrays.asList(new VisualShowTurtle(myID));
        }
    }
    private List<ImmutableVisualCommand> setPen(Integer isDown){
        myPenState = isDown;
        if (isDown == 0.0) {
            return Arrays.asList(new VisualPenUp(myID));
        }
        else {
            return Arrays.asList(new VisualPenDown(myID));
        }
    }

    private List<ImmutableVisualCommand> setTowards(Double newX, Double newY) {
        double deltaX = newX - myXCoor;
        double deltaY = newY - myYCoor;
        double degrees = Math.atan2( newY - myYCoor,  newX - myXCoor) * 180.0 /Math.PI;


        System.out.println("DEGREE TURN PRETRANSFORMATION: " + degrees);

        if (deltaX >= 0 & deltaY >= 0)
            degrees = 90 - degrees;
        else if ( deltaX > 0 & deltaY < 0)
            degrees = 90 + Math.abs(degrees);
        else if (deltaX < 0 & deltaY < 0)
            degrees = 90 + Math.abs(degrees);
        else
            degrees = 270 + 180 - degrees;
//        if (newY < myYCoor){
//            degrees = 180 + degrees;
//        }
//        double calcHeading = 0;
//        if (myHeading > 180)
//            calcHeading = 450 - myHeading;
//        else if (myHeading < 90)
//            calcHeading = 90 - myHeading;
//        else
//            calcHeading = 270 + 180 - myHeading;
//
//        double incorrectForm = calcHeading + degrees;
//
//        double finalHeading = 0.0;
//        if (incorrectForm < 90)
//            finalHeading = 90 - incorrectForm;
//        else if (incorrectForm < 180)
//            finalHeading = 270 + (180 - incorrectForm);
//        else if (incorrectForm < 270)
//            finalHeading = 270 + (180 - incorrectForm);
//        else
//            finalHeading = 360 - incorrectForm + 90;
////        if (deltaX > 0){
////            //do nothing
////        }
////        else if (deltaY >= 0 & deltaX < 0){
////            degrees = 450 - degrees;
////        }
////        else if (deltaY < 0 & deltaX < 0){
////            degrees = Math.abs(-90 + degrees);
////        }
////        else if (deltaY > 0 & deltaX == 0)
////            degrees = 180;
////        else if (deltaY < 0 & deltaX == 0)
////            degrees = -180;
////        else
////            degrees = 0;
//        double currentATanHeading = 0;
//        if (myHeading <= 90 )
//            currentATanHeading = 90 - myHeading;
//        else if (myHeading <= 180)
//            currentATanHeading = -90.0 + Math.abs(180 - myHeading);
//        else if (myHeading <= 270)
//            currentATanHeading = 270 - myHeading;
//        else
//            currentATanHeading = -1.0 * (myHeading - 270);


//        if (Math.signum(deltaX) > 0 & Math.signum(deltaY) > 0)
//            degrees = 90 - degrees;
//        else if (Math.signum(deltaX) > 0 & Math.signum(deltaY) < 0) {
//            degrees = Math.abs(degrees) + 90;
//        }
//        else if (Math.signum(deltaX) < 0 & Math.signum(deltaY) < 0)
//            degrees = Math.abs(-90 + degrees);
//        else
//            degrees = -1.0 * (180 - degrees);
        double turned = degrees - myHeading;
        System.out.println("DEGREE TURN POST: " + turned);
        System.out.println("CURRENT HEADING: " + myHeading);
        myHeading = myHeading + turned;
        return Arrays.asList(new VisualTurtleTurn(myID,  turned));
    }

    private void checkHeading(){
        if (myHeading > FULL_CIRCLE) {
            myHeading = myHeading % FULL_CIRCLE;
        }
        if (myHeading < 0) {
            myHeading = myHeading % FULL_CIRCLE;
            myHeading = myHeading + FULL_CIRCLE;

        }
    }
}
