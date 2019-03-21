package nodes.arithmetics;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Sine extends CommandNode {
    public Sine(String commandName) {
        super(commandName);
    }
    /**
     * @return Sine value of child node
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        return Math.sin(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
    }
}

