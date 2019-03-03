package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MultipleTurtle extends CommandNode {
    public MultipleTurtle(String a){
        super(a);
    }
    @Override
    public abstract double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles);

    protected List<Integer> getSortedTurtleIDs(CommandNode myListNode, List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        List<Integer> myTurtleIDs = new ArrayList<Integer>();
        for (CommandNode grandChild: myListNode.getChildren())
            myTurtleIDs.add((int)(grandChild.evaluate(myVisCommands, myTurtles)));
        Collections.sort(myTurtleIDs);
        return myTurtleIDs;
    }
    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }



}
