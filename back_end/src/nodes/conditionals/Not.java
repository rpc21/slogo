package nodes.conditionals;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Not extends BooleanNode {
    private static final double ZERO = 0;
    private static final double ONE = 1;
    public Not(String a) {
        super(a);
    }
    /**
     * @return whether or not child evaluates to 0
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        if (super.getFirstExpression(myVisCommands, myTurtles) == 0) {
            return ONE;
        }
        return ZERO;
    }
}