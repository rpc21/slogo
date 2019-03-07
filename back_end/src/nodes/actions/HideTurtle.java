package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;

public class HideTurtle extends TurtleCommand {
    private static final String methodName = "setVisibility";
    public HideTurtle(String name){
        super(name);
    }
    @Override public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{0},myTurtles));
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
