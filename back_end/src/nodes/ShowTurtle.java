package nodes;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class ShowTurtle extends TurtleCommand {
    private static final String methodName = "setVisibility";
    public ShowTurtle(String name){
        super(name);
    }
    @Override public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{1},myTurtles));
        return 1;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}

