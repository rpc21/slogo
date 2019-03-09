package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;

public class SetHeading extends TurtleCommand {
    public SetHeading(String name){
        super(name);
    }
    private static final String methodName = "setHeading";


    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double currHeading = myTurtles.getLastActiveTurtle().getHeading();
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{degrees},myTurtles));
        return Math.abs(currHeading - degrees);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}
