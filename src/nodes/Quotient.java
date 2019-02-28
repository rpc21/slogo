package nodes;
import apis.ImmutableVisualCommand;
import nodes.VisualCommand;

import java.util.List;


public class Quotient extends CommandNode {
    private static final int INVALID_INPUT = 0;

    public Quotient(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands);
        if (validDenominator(secondExpression))
            return firstExpression / secondExpression;
        else {
            throw new IllegalArgumentException();
        }
    }

    private boolean validDenominator(double d){
        return d == 0.0;
    }

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}