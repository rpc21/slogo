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
public class Cosine extends CommandNode {
    public Cosine(String commandName) {
        super(commandName);
    }
    /**
     * @return Cosine value of child node
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        return Math.cos(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
    }
}
