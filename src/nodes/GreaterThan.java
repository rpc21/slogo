package nodes;
import apis.ImmutableVisualCommand;
import main.Turtle;
import nodes.VisualCommand;

import java.util.List;

public class GreaterThan extends BooleanNode{
    private static final int INVALID_INPUT = 0;

    public GreaterThan(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle)  {
        if (super.getFirstExpression(myVisCommands, myTurtle) > super.getSecondExpression(myVisCommands, myTurtle))
            return ONE;
        return ZERO;
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