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
public class Random extends CommandNode {
    public Random(String commandName) {
        super(commandName);
    }
    /**
     * @return random integer between 0 and first child's value, exclusive of upper bound
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        int childValue = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        return Math.floor(Math.random()* (childValue));

    }
}
