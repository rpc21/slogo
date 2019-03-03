package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class GetShape extends CommandNode{
    public GetShape(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.get(0).getShape();
    }
}
