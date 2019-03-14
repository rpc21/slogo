package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;

public class SetPosition extends TurtleCommand {
    private static final String methodName  = "setPosition";
    public SetPosition(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double newXCoor = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double newYCoor = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        double oldXCoor = myTurtles.getLastActiveTurtle().getXCoor();
        double oldYCoor = myTurtles.getLastActiveTurtle().getYCoor();

        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{newXCoor,newYCoor}, myTurtles));

        return Math.pow(Math.abs(oldXCoor - newXCoor),2)  +
                Math.pow(Math.abs(oldYCoor - newYCoor),2);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}