package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.ArrayList;
import java.util.List;

public class Ask extends MultipleTurtle{
    public Ask(String a){
        super(a);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        CommandNode myListNode = super.getChildren().get(0);
        List<Integer> myTurtleIDs = super.getSortedTurtleIDs(myListNode,myVisCommands,myTurtles);
        myTurtles.setActiveTurtles(myTurtleIDs);
        double ret = 0;
        for (int k = 0; k < myChildren.size(); k++) {
            ret = myChildren.get(k).evaluate(myVisCommands, myTurtles);
        }
        return ret;
    }
    /*
     * Ask can have an unlimited number of children
     */
    @Override
    public void addChild(CommandNode c){
        super.addChild(c);
    }

}
