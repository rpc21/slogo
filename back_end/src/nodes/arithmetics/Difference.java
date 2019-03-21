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
public class Difference extends CommandNode {
    public Difference(String a) {
        super(a);
    }
    /**
     * @return difference between first and second children's values
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        return firstExpression - secondExpression;
    }
}