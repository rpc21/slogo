package nodes.conditionals;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Or extends BooleanNode {
    private static final double ZERO = 0;
    private static final double ONE = 1;
    public Or(String a) {
        super(a);
    }
    /**
     * @return whether or not first or second children evaluate to be non-zero
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        if (super.getFirstExpression(myVisCommands, myTurtles) != ZERO ||  super.getSecondExpression(myVisCommands, myTurtles) != ZERO)
            return ONE;
        return ZERO;
    }
}