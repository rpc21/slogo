package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class PenDown extends TurtleCommand {
    private static final String methodName = "setPen";
    public PenDown(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{1},myTurtles));
        return 1;
    }
}
