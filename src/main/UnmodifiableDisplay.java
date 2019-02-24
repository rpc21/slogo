package main;

public abstract class UnmodifiableDisplay{

    /**
     * returns calculation of Display's x_position
     */
    public abstract double getXPosition();
    /**
     * returns calculation of Display's y_position
     */
    public abstract double getYPosition();
    /**
     * returns calculation of Display's orientation (counterclockwise degrees)
     */
    public abstract double getOrientation();
    /**
     * returns whether or not change in position requires drawing or not
     */
    public abstract boolean getPenDown();

}
