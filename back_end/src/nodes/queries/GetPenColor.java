package nodes.queries;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;

public class GetPenColor extends CommandNode {
    public GetPenColor(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.get(myTurtles.getActiveID()).getPenColor();
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
