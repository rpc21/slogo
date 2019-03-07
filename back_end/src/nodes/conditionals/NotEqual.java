package nodes.conditionals;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.conditionals.BooleanNode;
import turtle.Bale;

import java.util.List;


public class NotEqual extends BooleanNode {

    public NotEqual(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        if (super.getFirstExpression(myVisCommands, myTurtles) !=
                super.getSecondExpression(myVisCommands, myTurtles)) {
            return ONE;
        }
        return ZERO;
    }

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}