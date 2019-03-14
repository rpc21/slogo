package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.TurtleCommand;

import turtle.Bale;

import java.util.List;

public class SetTowards extends TurtleCommand {
    public SetTowards(String name){
        super(name);
    }
    private static final String methodName = "setTowards";


    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double towardsX=  super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double towardsY =  super.getChildren().get(1).evaluate(myVisCommands, myTurtles);

        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{towardsX,towardsY},myTurtles));

        double currHeading = myTurtles.getHeading();
        return Math.abs(currHeading - myTurtles.getHeading());
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}
