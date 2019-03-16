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
public class PenUp extends TurtleCommand {
    private static final String methodName = "setPen";
    public PenUp(String name){
        super(name);
    }
    /**
     * Sets associated method name for myTurtles to handle as setPen, which takes in a 0 or 1 to denote whether the pen
     * is up or down respectively before invoking turtles to add associated visual commands
     * @return 0, pen will be up and not draw on canvas if turtle moves
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{0},myTurtles));
        return 0;
    }
}
