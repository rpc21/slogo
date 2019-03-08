package nodes.queries;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;

public class IsShowing extends CommandNode {
    public IsShowing(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.getVisibility();
    }
}