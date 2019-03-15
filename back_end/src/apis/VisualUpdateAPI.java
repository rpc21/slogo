package apis;

import java.util.List;

public interface VisualUpdateAPI {

    /**
     * Create numTurtles number of turtles and add them to the canvas at position 0,0
     * All the turtles that are created are assigned an id that allows the user to access that turtle
     * and give instructions specific to the turtle
     * @param numTurtles number of turtles to be added
     */
    void addTurtles(int numTurtles);

    /**
     * Give the turtles with the IDs in the the activeTurtleIDs list the appearance of being active
     * @param activeTurtleIDs list of IDs of the turtles that should have the appearance of being active
     */
    void setActiveTurtles(List<Integer> activeTurtleIDs);

    /**
     * Add a variable to the variable explorer that the user can click on and change
     * @param name the name of the variable
     * @param val the value stored in the variable name
     */
    void addVariable(String name, Double val);

    /**
     * Add a method to the list of user defined methods.  Include the name of the methods as well as the names of the
     * parameters of the method
     * @param name name of the user defined method
     * @param myVars list of the parameters for the method
     */
    void addMethod(String name, List<String> myVars);

    /**
     *
     * @param id
     * @param x
     * @param y
     */
    void turtleMove(int id, double x, double y);

    /**
     * Make the turtle with id id turn degrees number of degrees
     * @param id turtle id of the turtle to turn
     * @param degrees number of degrees the turtle should change
     */
    void turtleTurn(int id,double degrees);
//    void turnRight(double degrees);
//    void turnLeft(double degrees);

    /**
     * Have the turtle with turtle id id raise its pen
     * @param id turtle id of the turtle to raise its pen
     */
    void setPenUp(int id);

    /**
     * Have the turtle with turtle id id set its pen down
     * @param id turtle id of the turtle to put its pen down
     */
    void setPenDown(int id);

    /**
     * Make the turtle with turtle id id appear on the screen
     * @param id turtle id of the turtle to be shown
     */
    void showTurtle(int id);

    /**
     * Make the turtle with turtle id id be hidden from the screen
     * @param id turtle id of the turtle to be hidden
     */
    void hideTurtle(int id);

    /**
     * Make the turtle with turtle id id face the direction specified by degrees
     * @param id turtle id of the turtle whose orientation is to be set
     * @param degrees orientation that the turtle should have
     */
    void setOrientation(int id, double degrees);

    /**
     *
     * @param id
     * @param degrees
     */
    void setTowards(int id, double degrees);//turn turtle to face this point

    /**
     * Change the location of the turtle with id id to position x, y on the canvas
     * @param id turtle id of the turtle to be moved
     * @param x resulting x position of the turtle
     * @param y resulting y position of the turtle
     */
    void setLocation(int id, double x, double y);//move turtle to these coordinates

    /**
     *
     * @param id
     */
    void goHome(int id);

    /**
     * Reset the canvas to white, erase all lines that are drawn, move all the turtles back to the center and have
     * them face due north
     */
    void clearScreen();

    /**
     * Change the background color of the canvas to be the color corresponding to index in the color palette
     * @param index index of the color in the color palette
     */
    void setBackgroundColor(int index);

    /**
     * Change the pen color of the turtle with id to be the color corresponding to index in the color palette
     * @param id id of the turtle whose pen is to be changed
     * @param index index of the color in the color palette
     */
    void setPenColor(int id, int index);

    /**
     * Change the pen size of the turtle with id to be pixels in size
     * @param id id of the turtle whose pen is to be changed
     * @param pixels new width of the pen
     */
    void setPenSize(int id, double pixels);

    /**
     * Change the icon of turtle with turtle id id to be the icon corresponding to index in the icon palette
     * @param id id of the turtle whose icon is to be changed
     * @param index index of the icon that the turtle is to be changed to in the icon palette
     */
    void setShape(int id, int index);

    /**
     * Set the index index of the color palette to be the color with RBG value (r,b,g)
     * @param index index in the color palette to be set
     * @param r red content (0-255) of the color to add
     * @param b blue content (0-255) of the color to add
     * @param g green content (0-255) of the color to add
     */
    void setPalette(int index, int r, int b, int g);


}
