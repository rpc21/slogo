package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class SetShape extends TurtleCommand {
    private static final String methodName = "setShape";
    public SetShape(String n){
        super(n);
    }
    /**
     * Evaluates child node to get index of new turtle shape before invoking Turtles to use "setShape" to invoke
     * appropriate turtles to accommodate this change and add associated visual commands
     * @return index of new turtle shape
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double index = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{index},myTurtles));
        return index;
    }

}
