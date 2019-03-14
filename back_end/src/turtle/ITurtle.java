package turtle;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 * The purpose of ITurtle is to abstract what these getter methods may mean when dealing with a singular turtle versus
 * a collection of turtles. In the latter's case, the values returned depend upon who is asking (whether they want the active
 * turtle's information or the last turtle to be updated's information). This also ensures none of the CommandNodes have to
 * directly deal with a singular turtle and only have to work with Bale, which is the "Collection" version of Turtle
 * @see Bale
 * @see Turtle
 */
public interface ITurtle {
    /**
     * @return id of turtle of concern
     */
    int getID();
    /**
     * @return pen color index of turtle of concern
     */
    int getPenColor();
    /**
     * @return shape index of turtle of concern
     */
    int getShape();
    /**
     * @return visibility state of turtle of concern
     */
    int getVisibility();
    /**
     * @return heading of turtle of concern
     */
    double getHeading();
    /**
     * @return pen state of turtle of concern
     */
    double getPenState();
    /**
     * @return x-coordinate of turtle of concern
     */
    double getXCoor();
    /**
     * @return y-coordinate of turtle of concern
     */
    double getYCoor();
}
