package nodes;

import turtle.Bale;
import apis.ImmutableVisualCommand;
import turtle.Turtle;
import java.util.List;

public class Tell extends MultipleTurtle {
    public Tell(String a){
        super(a);
    }

    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        CommandNode myListNode = myChildren.get(0);
        List<Integer> myTurtleIDs = super.getSortedTurtleIDs(myListNode,myVisCommands,myTurtles);
        int maxTurtleID = myTurtleIDs.get(myTurtleIDs.size() - 1);
        int currMaxTurtleID = myTurtles.size() -1;
        myVisCommands.add(new VisualMakeTurtles(maxTurtleID - myTurtles.size() + 1));
        for (int iter = myTurtles.size() -1; iter < maxTurtleID; iter++)
            myTurtles.add(new Turtle(iter));
        return currMaxTurtleID;
    }

    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
