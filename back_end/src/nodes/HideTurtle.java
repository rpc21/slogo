package nodes;
import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class HideTurtle extends TurtleCommand {
    private static final String methodName = "setVisibility";
    public HideTurtle(String name){
        super(name);
    }
    @Override public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{0},myTurtles));
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
