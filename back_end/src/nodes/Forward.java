package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class Forward extends TurtleCommand {

    public Forward(String name){
        super(name);
    }
    private static final String methodName = "moveTurtle";
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double pixels = myChildren.get(0).evaluate(myVisCommands, myTurtles);
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{pixels},myTurtles));
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
