package nodes.queries;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;

public class Turtles extends CommandNode {
    public Turtles(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.size();
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
