package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class GetPenColor extends CommandNode{
    public GetPenColor(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.get(myTurtles.getActiveID()).getPenColor();
    }
}
