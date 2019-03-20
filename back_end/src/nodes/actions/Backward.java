package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.TurtleCommand;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Backward extends TurtleCommand {
    private static final String methodName = "move";
    public Backward(String name){
        super(name);
    }

    /**
     * Evaluates child node to get the number of pixels associated with backward movement, and sets associated
     * method name for myTurtles to handle as "move" before invoking turtles to add associated visual commands
     * @return pixels moved
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double pixels = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        return -1.0 * super.invokeTurtles(-1.0 * pixels,methodName,myVisCommands,myTurtles);
    }

}
