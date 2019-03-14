package nodes.conditionals;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class And extends BooleanNode {
    private static final double ZERO = 0;
    private static final double ONE = 1;
    public And(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        if (super.getFirstExpression(myVisCommands, myTurtles) != ZERO &
                super.getSecondExpression(myVisCommands, myTurtles) != ZERO)
            return ONE;
        return ZERO;
    }
}