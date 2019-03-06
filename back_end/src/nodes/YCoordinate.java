package nodes;

import apis.ImmutableVisualCommand;
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