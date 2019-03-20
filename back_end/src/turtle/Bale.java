package turtle;

import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
  * @author Anna Darwish
 *  @version 3/13/2019
  * Bale extends the specific implementation of ListNode, ArrayList, because it was not necessary to implement the abstract methods
  * in ListNode. Bale is biological jargon for a group of turtles, and it handles regulating which turtles are active under
  * various conditions and keeping track of which turtle is being updated. The getters at the bottom of this class needed
 * to be public in order for the command nodes to access information directly from Bale concerning the turtles and also allows
 * for Turtle and Bale to implement the same interface
 */
public class Bale extends ArrayList<Turtle> implements ITurtle {
    private int myActiveID;
    private List<Integer> myActiveIDs;
    public Bale(){
        myActiveID = 0;
        myActiveIDs = new ArrayList<>();
        myActiveIDs.add(0);
    }


    /**
     * Returns list of all ids. In current implementation, this is technically just a list of integers from 0 to the
     * number of turtles in existence, but this makes our code flexible to unordered ids
     */
    public List<Integer> getAllIDs(){
        List<Integer> myActiveTurtleIDs = new ArrayList<>();
        for (Turtle t: this) {
            myActiveTurtleIDs.add(t.getID());
        }
        return Collections.unmodifiableList(myActiveTurtleIDs);
    }
    /**
     * Returns list of all active ids
     */
    public List<Integer> getActiveTurtleIDs(){
        return Collections.unmodifiableList(myActiveIDs);
    }

    /**
     * Determines maximum ID in list of integers and creates the necessary number of turtles to align with this maximum
     * id
     */
    public void makeTurtles(List<Integer> myIDs){
        int maxID = -1;
        for (Integer id: myIDs) {
            if (id > maxID)
                maxID = id;
        }
        makeTurtlesUpTo(maxID);

    }

    private void makeTurtlesUpTo(int maxID){
        for (int k = size() - 1; k < maxID; k++) {
            this.add(new Turtle(k + 1));
        }
    }
    /**
     * Sets all turtles in given list of IDs to be active and inactive otherwise
     * @param myTurtleIDs list of Integer ids of turtles to be set as active
     */
    public void setActiveTurtles(List<Integer> myTurtleIDs){
        myActiveIDs.clear();
        for (Turtle t: this)
            if (myTurtleIDs.contains(t.getID())) {
                myActiveIDs.add(t.getID());
                t.setActive(true);
            }
            else {
                t.setActive(false);
            }
    }
    /**
     * Sets singular turtle with myTurtleID as active, and sets the rest to be inactive
     * @param myTurtleID id of turtle to be set as active
     */
    public void setActiveTurtles(int myTurtleID){
        myActiveIDs.clear();
        for (Turtle t: this)
            if (! (t.getID() == myTurtleID)) {
                t.setActive(false);
            }
            else {
                myActiveIDs.add(myTurtleID);
                myActiveID = myTurtleID;
                t.setActive(true);
            }
    }

    /**
     * Invokes each active turtle to act perform the list of turtle methods using the associated parameters for the method(s)
     * Collects any visual commands associated with the turtle methods and returns them to the CommandNode that invoked
     * Bale
     * @return list of ImmutableVisualCommands to be executed by the GUI
     * @see Turtle
     * @see ImmutableVisualCommand
     */
    public List<ImmutableVisualCommand> act(List<String> myTurtleMethods,  Object[] actualParams) throws InvalidInputException {
        List<ImmutableVisualCommand> myVisCommands = new ArrayList<>();
        List<Turtle> iterateTurtle = this.getActiveTurtles();
        for (Turtle t: iterateTurtle) {
            myActiveID = t.getID();
            for (String m: myTurtleMethods) {
                myVisCommands.addAll(t.turtleAction(m, actualParams));
            }
        }
        return myVisCommands;
    }

    private List<Turtle> getActiveTurtles(){
        List<Turtle> activeTurtles = new ArrayList<>();
        for (Turtle t: this) {
            if (t.getIsActive()) {
                activeTurtles.add(t);
            }
        }
        return activeTurtles;
    }

    /**
     * Returns state information associated with the last active turtle. This method temporarily sets this turtle to be
     * active as it is the one being queried before restoring it to the most recent active turtle
     * @param stateInformation specifies what information has been requested
     * @return value associated with the state
     * @throws InvalidCommandException if the stateInformation is not a valid member of a Turtle
     */
    public double getLastActiveState(String stateInformation) throws InvalidCommandException  {
        try {
            int currentID = myActiveID;
            for (Integer id: myActiveIDs)
                if (myActiveID < id)
                    myActiveID = id;
            Method m = getClass().getDeclaredMethod(stateInformation);
            Object ret = m.invoke(this);
            myActiveID = currentID;
            return (Double)ret;
        }
        catch(Exception e) {
            throw new InvalidCommandException(stateInformation);
        }
    }

    /**
     * Returns the pen color index of the current active turtle
     */
    @Override
    public int getPenColor() {
        return this.get(myActiveID).getPenColor();
    }
    /**
     * Returns the shape index of the current active turtle
     */
    @Override
    public int getShape() {
        return this.get(myActiveID).getShape();
    }
    /**
     * @return the heading of the current active turtle
     */
    @Override
    public double getHeading() {
        return this.get(myActiveID).getHeading();
    }
    /**
     * @return the id of the current active turtle
     */
    @Override
    public int getID() {
        return myActiveID;
    }
    /**
     * @return the pen state of the current active turtle. This is currently a double in case we ever wanted to tie
     * in opacity into the state of the pen
     * @see Turtle
     */
    @Override
    public double getPenState() {
        return this.get(myActiveID).getPenState();
    }
    /**
     * @return the visibility state of the current active turtle
     */
    @Override
    public int getVisibility() {
        return this.get(myActiveID).getVisibility();
    }
    /**
     * @return the x-coordinate of the current active turtle
     */
    @Override
    public double getXCoor() {
        return this.get(myActiveID).getXCoor();
    }
    /**
     * @return the y-coordinate of the current active turtle
     */
    @Override
    public double getYCoor() {
        return this.get(myActiveID).getYCoor();
    }
}
