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
public class Quotient extends CommandNode {

    public Quotient(String a) {
        super(a);
    }
    /**
     * @return first child's value divided by second child's value. Checks to make sure user didn't try to divide by 0
     * or NaN
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        if (validDenominator(secondExpression)) {
            return firstExpression / secondExpression;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private boolean validDenominator(double d){
        return d != 0.0 && !Double.isNaN(d);
    }
}