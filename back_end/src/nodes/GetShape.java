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
        int activeID = myTurtles.getActiveID();
        try {
            return myTurtles.get(activeID).getShape();
        }
        catch(Exception e){
            return myTurtles.get(0).getShape();
        }
    }
}
