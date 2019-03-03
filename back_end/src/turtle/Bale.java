package turtle;

import nodes.CommandNode;

import java.util.ArrayList;
import java.util.List;

/*
  * Bale extends the specific implementation of ListNode, ArrayList, because it was not necessary to implement the abstract methods
  * in ListNode. Bale is biological jargon for a group of turtles :)
 */

public class Bale extends ArrayList<Turtle> {
    private int myActiveID;
    public Bale(){
        myActiveID = 0;
    }
    public List<Turtle> getActiveTurtles(){
        List<Turtle> activeTurtles = new ArrayList<>();
        for (Turtle t: this) {
            if (t.isActive())
                activeTurtles.add(t);
        }
        return activeTurtles;
    }
    public List<Integer> getActiveTurtlesIDs(){
        List<Integer> myActiveTurtleIDs = new ArrayList<>();
        for (Turtle t: getActiveTurtles())
            myActiveTurtleIDs.add(t.getID());
        return myActiveTurtleIDs;
    }

    public void setActiveTurtles(List<Integer> myTurtleIDs){
        for (Turtle t: this)
            if (myTurtleIDs.contains(t.getID()))
                t.setActive(true);
            else
                t.setActive(false);
    }
    public void setAllInactive(){
        for (Turtle t: this)
            t.setActive(false);
    }

    public void setActiveID(int id){
        myActiveID = id;
    }

    public int getActiveID(){
        return myActiveID;
    }

}
