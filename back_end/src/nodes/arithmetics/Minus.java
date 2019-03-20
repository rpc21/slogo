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
public class Minus extends CommandNode {
    public Minus(String commandName) {
        super(commandName);
    }
    /**
     * @return Negation of child node
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        return -1.0 * super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
    }
}
