package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;


public class Not extends BooleanNode{

    public Not(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        if (super.getFirstExpression(myVisCommands) == ZERO)
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
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}