package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class XCoordinate extends CommandNode {
    public XCoordinate(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.get(myTurtles.getActiveID()).getXCoor();
    }
}
