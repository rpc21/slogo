package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class Left extends TurtleCommand {

    public Left(String name){
        super(name);
    }
    private static final String methodName = "turn";
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double degrees = myChildren.get(0).evaluate(myVisCommands, myTurtles);
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{-1.0* degrees},myTurtles));
        return degrees;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}