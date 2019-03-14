package nodes.arithmetics;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
import static java.lang.Double.NaN;

/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class NaturalLog extends CommandNode {

    public NaturalLog(String commandName) {
        super(commandName);
    }
    /**
     * @return natural log value of child node. Checks to see whether argument would throw an error, such as if the
     * user tried to take the log of a negative value
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double argument = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        if (argument <= 0 || argument == NaN)
            throw new IllegalArgumentException();
        return Math.log(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
    }
}
