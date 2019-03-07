package nodes;

import turtle.Bale;
import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.ArrayList;
import java.util.List;

public class Tell extends CommandNode {
    public Tell(String a){
        super(a);
    }

    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        System.out.println("TELL COMMAND WAS CREATED AND EVALUATED");
        CommandNode myListNode = myChildren.get(0);
        List<Integer> myTurtleIDs = new ArrayList<>();
        double ret = 0.0;
        int maxID = -1;

        for (CommandNode c: myListNode.getChildren()) {
            ret = c.evaluate(myVisCommands, myTurtles);
            if ((int)ret > 0) {
                myTurtleIDs.add((int) ret - 1);
                if (((int) ret) > maxID)
                    maxID = (int) ret;
            }
        }

        int currentTurtleCount = myTurtles.size();
        myTurtles.makeTurtlesUpTo(maxID);
        myTurtles.setActiveTurtles(myTurtleIDs);
        myVisCommands.add(new VisualActiveTurtles(myTurtleIDs));
        if (currentTurtleCount != myTurtles.size())
            myVisCommands.add(new VisualTurtleTell(myTurtles.size()-currentTurtleCount));
        return ret;
    }

    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
