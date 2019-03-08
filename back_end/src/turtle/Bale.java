package turtle;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

/*
  * Bale extends the specific implementation of ListNode, ArrayList, because it was not necessary to implement the abstract methods
  * in ListNode. Bale is biological jargon for a group of turtles :)
 */

public class Bale extends ArrayList<Turtle> implements TurtleInterface{
    private int myActiveID;
    public Bale(){
        myActiveID = 0;
    }
    private List<Turtle> getActiveTurtles(){
        List<Turtle> activeTurtles = new ArrayList<>();
        for (Turtle t: this) {
            if (t.isActive()) {
                activeTurtles.add(t);
            }
        }
        return activeTurtles;
    }

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

    public void setActiveTurtles(List<Integer> myTurtleIDs){
        for (Turtle t: this)
            if (myTurtleIDs.contains(t.getID())) {
                t.setActive(true);
            }
            else {
                t.setActive(false);
            }
    }
    public void setMyActiveID(int id){
        myActiveID = id;
    }

    public List<ImmutableVisualCommand> act(List<String> myTurtleMethods,  Object[] actualParams) throws InvalidInputException {
        List<ImmutableVisualCommand> myVisCommands = new ArrayList<>();
        for (Turtle t: this.getActiveTurtles()) {
            setActiveID(t.getID());
            for (String m: myTurtleMethods) {
                myVisCommands.addAll(t.turtleAction(m, actualParams));
            }

        }
        return myVisCommands;
    }

    public Turtle getLastActiveTurtle(){
        return this.get(size() - 1);
    }

    private void setActiveID(int id){
        myActiveID = id;
    }

    public int getActiveID(){
        return myActiveID;
    }


    @Override
    public int getPenColor() {
        return this.get(myActiveID).getPenColor();
    }

    @Override
    public int getShape() {
        return this.get(myActiveID).getShape();
    }

    @Override
    public double getHeading() {
        return this.get(myActiveID).getHeading();
    }

    @Override
    public int getID() {
        return getActiveID();
    }

    @Override
    public double getPenState() {
        return this.get(myActiveID).getPenState();
    }

    @Override
    public int getVisibility() {
        return this.get(myActiveID).getVisibility();
    }

    @Override
    public double getXCoor() {
        return this.get(myActiveID).getXCoor();
    }

    @Override
    public double getYCoor() {
        return this.get(myActiveID).getYCoor();
    }
}
