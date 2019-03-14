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
public class Left extends TurtleCommand {
    private static final String methodName = "turn";
    public Left(String name){
        super(name);
    }
    /**
     * Evaluates child node to get the number of degrees associated with left turn, and sets associated
     * method name for myTurtles to handle as "turn" before invoking turtles to add associated visual commands
     * @return degrees turned
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{-1.0* degrees},myTurtles));
        return degrees;
    }
}