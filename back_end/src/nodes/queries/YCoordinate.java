package nodes.queries;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;

public class YCoordinate extends CommandNode {
    public YCoordinate(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.get(myTurtles.getActiveID()).getYCoor();
    }
}