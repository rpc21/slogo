package nodes.queries;

import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;

public class GetShape extends CommandNode {
    public GetShape(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.getShape();
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
