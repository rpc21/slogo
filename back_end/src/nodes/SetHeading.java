package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class SetHeading extends TurtleCommand {
    public SetHeading(String name){
        super(name);
    }
    private static final String methodName = "setHeading";

    //TODO figure out how to return most recent heading from turtle...perhaps this is a design flaw
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double currHeading = myTurtles.getLastActiveTurtle().getHeading();
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{degrees},myTurtles));
        return Math.abs(currHeading - degrees);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
