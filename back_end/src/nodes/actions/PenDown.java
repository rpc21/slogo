package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;

public class PenDown extends TurtleCommand {
    private static final String methodName = "setPen";
    public PenDown(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{1},myTurtles));
        return 1;
    }
}
