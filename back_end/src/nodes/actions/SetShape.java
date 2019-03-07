package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;

public class SetShape extends TurtleCommand {
    private static final String methodName = "setShape";
    public SetShape(String n){
        super(n);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double index = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{index},myTurtles));
        return index;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }

}
