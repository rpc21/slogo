package nodes.conditionals;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class GreaterThan extends BooleanNode {
    private static final double ZERO = 0;
    private static final double ONE = 1;
    public GreaterThan(String a) {
        super(a);
    }
    /**
     * @return whether or not first child's value is greater than that of second child
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        if (super.getFirstExpression(myVisCommands, myTurtles) >
                super.getSecondExpression(myVisCommands, myTurtles)) {
            return ONE;
        }
        return ZERO;
    }
}