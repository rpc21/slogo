package nodes.structures;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualActiveTurtles;
import nodes.visuals.VisualTurtleTell;
import turtle.Bale;

import java.util.ArrayList;
import java.util.List;

public class Tell extends CommandNode {
    public Tell(String a){
        super(a);
    }

    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {

        CommandNode myListNode = super.getChildren().get(0);
        List<Integer> myTurtleIDs = new ArrayList<>();
        double ret = 0.0;

        for (CommandNode c: myListNode.getChildren()) {
            ret = c.evaluate(myVisCommands, myTurtles);
            if ((int) ret > 0) {
                myTurtleIDs.add((int) ret - 1);
            }
        }
        int currentTurtleCount = myTurtles.size();

        myTurtles.makeTurtles(myTurtleIDs);
        myTurtles.setActiveTurtles(myTurtleIDs);

        myVisCommands.add(new VisualActiveTurtles(myTurtleIDs));
        if (currentTurtleCount != myTurtles.size()) {
            myVisCommands.add(new VisualTurtleTell(myTurtles.size() - currentTurtleCount));
        }
        return ret;
    }

    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
