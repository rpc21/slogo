package nodes.arithmetics;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Power extends CommandNode {
    public Power(String commandName) {
        super(commandName);
    }
    /**
     * @return first child's value raised to second child's value
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double base = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double exp = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        return Math.pow(base,exp);
    }
}
