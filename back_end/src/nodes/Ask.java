package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.ArrayList;
import java.util.List;

public class Ask extends CommandNode{
    public Ask(String a){
        super(a);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        CommandNode myListNode = super.getChildren().get(0);
        List<Integer> myTurtleIDs = new ArrayList<>();
        double ret = 0.0;
        for (CommandNode c: myListNode.getChildren()) {
            ret = c.evaluate(myVisCommands, myTurtles);
            if ((int)ret > 0 & (int)ret < myTurtles.size())
                myTurtleIDs.add((int)ret);
        }
        myTurtles.setActiveTurtles(myTurtleIDs);
        myVisCommands.add(new VisualActiveTurtles(myTurtleIDs));
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
