package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.TurtleCommand;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 **/
public class Forward extends TurtleCommand {
    private static final String methodName = "move";
    public Forward(String name){
        super(name);
    }

    /**
     * Evaluates child node to get the number of pixels associated with forward movement, and sets associated
     * method name for myTurtles to handle as "move" before invoking turtles to add associated visual commands
     * @return pixels moved
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double pixels = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        return super.invokeTurtles(pixels,methodName,myVisCommands,myTurtles);
    }
}
