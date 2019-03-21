package turtle;

import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.external.InvalidInputException;
import nodes.visuals.VisualPenDown;
import nodes.visuals.VisualPenUp;
import nodes.visuals.VisualTurtlePosition;
import nodes.visuals.VisualTurtleTurn;
import nodes.visuals.VisualHomeTurtle;
import nodes.visuals.VisualPenColor;
import nodes.visuals.VisualPenSize;
import nodes.visuals.VisualTurtleHeading;
import nodes.visuals.VisualTurtleShape;
import nodes.visuals.VisualHideTurtle;
import nodes.visuals.VisualShowTurtle;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 * This class has a list of public getters in order to implement a common Interface with Bale (ITurtle).  This class uses
 * reflection to determine which method to invoke to update the current state of the turtle and return the appropriate visual
 * commands, which is why they are all private and mistakenly labelled as "unused"
 */
public class Turtle implements ITurtle {

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
    private static final double QUARTER_CIRCLE = 90;
    private static final double HALF_CIRCLE = 180;
    private static final double THREE_QUARTER_CIRCLE = 270;

    public Turtle(int id){
        myXCoor = myYCoor = myHeading = 0;
        myPenSize = 2;
        myID = id;
        myVisibility = myPenState = myShape = myPenColor = 1;
        if (id == 0) {
            isActive = true;
        }
    }
    /**
     * Returns x-coordinate associated with this Turtle
     */
    public double getXCoor(){ return myXCoor; }
    /**
     * Returns y-coordinate associated with this Turtle
     */
    public double getYCoor() { return myYCoor; }
    /**
     * Returns heading associated with this Turtle
     */
    public double getHeading(){ return myHeading; }
    /**
     * Returns pen color associated with this Turtle
     */
    public int getPenColor(){ return myPenColor;}
    /**
     * Returns shape associated with this Turtle
     */
    public int getShape(){ return myShape; }
    /**
     * Returns ID associated with this Turtle
     */
    public int getID(){return myID;}
    /**
     * Returns visibility state associated with this Turtle
     */
    public int getVisibility(){ return myVisibility; }
    /**
     * Returns pen state associated with this Turtle
     */
    public double getPenState(){ return myPenState; }

    /**
     * Uses reflection to invoke the appropriate method associated with the first parameter and determine the methods
     * signature from the list of object parameters.
     * @param actionName is the name of the method to be invoked
     * @param myParams is the set of parameters associated with this method
     * @return list of immutable visual commands associated with a particular state update
     * @throws InvalidInputException if the method signature does not exist in this class, or the method does not
     *         return a list of ImmutableVisualCommands
     */
    List<ImmutableVisualCommand> turtleAction(String actionName, Object[] myParams) throws InvalidInputException {
        try {

            Class<?>[] typeOfParams = new Class<?>[myParams.length];
            for (int i = 0; i < myParams.length; i++)
                typeOfParams[i] = myParams[i].getClass();
            Method m = getClass().getDeclaredMethod(actionName,typeOfParams);
            Object ret = m.invoke(this, myParams);
            return (List<ImmutableVisualCommand>)ret;
        }
        catch (Exception e){
            throw new InvalidCommandException(actionName);
        }
    }

    void setActive(boolean a){isActive = a;}
    boolean getIsActive(){return isActive;}
    /**
     * Either performs usual movement given current heading of the turtle, or if the turtle moves out of bounds,
     * calculates appropriate x and y coordinate on opposite side of the canvas for display purposes
     */
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
    /**
     * In order to display moving out of bounds properly, the turtle is moved to the edge of the canvas under the current
     * conditions of its pen, then to the opposite side of the canvas with its pen up (as to not draw a misleading
     * line across the entire canvas). Then the pen is restored to its original state before moving the turtle
     * the remaining distance it has to move
     */
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
    private void setYCoor(double y){ myYCoor = y; }

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

    private List<ImmutableVisualCommand> setPenSize(Double pixels){
        myPenSize = pixels;
        return Arrays.asList(new VisualPenSize(myID,pixels));
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
    /**
     * The degrees needed to turn are calculated using the Math class's arc-tangent function, which assumes a unit circle
     * offset and positive counter-clockwise motion. Our display assumes that 0 degrees is straight up with a positive
     * clockwise motion, which is why the degrees returned by Math's arc-tangent function must be converted to our
     * coordinate system depending upon the quadrant it is located in
     */
    private List<ImmutableVisualCommand> setTowards(Double newX, Double newY) {
        double deltaX = newX - myXCoor;
        double deltaY = newY - myYCoor;
        double degrees = Math.atan2( newY - myYCoor,  newX - myXCoor) * HALF_CIRCLE /Math.PI;


        if (deltaX >= 0 & deltaY >= 0)
            degrees = QUARTER_CIRCLE - degrees;
        else if ( deltaX > 0 & deltaY < 0)
            degrees = QUARTER_CIRCLE + Math.abs(degrees);
        else if (deltaX < 0 & deltaY < 0)
            degrees = QUARTER_CIRCLE + Math.abs(degrees);
        else
            degrees = THREE_QUARTER_CIRCLE + HALF_CIRCLE - degrees;

        double turned = degrees - myHeading;
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
