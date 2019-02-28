package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;




public abstract class BooleanNode extends CommandNode {
    private static final int INVALID_INPUT = 0;

    public BooleanNode(String a) {
        super(a);
    }
    protected double getFirstExpression(List<ImmutableVisualCommand> myVisCommands){
        return super.getChildren().get(0).evaluate(myVisCommands);
    }
    protected double getSecondExpression(List<ImmutableVisualCommand> myVisCommands){
        return super.getChildren().get(1).evaluate(myVisCommands);
    }

    @Override
    public abstract double evaluate(List<ImmutableVisualCommand> myVisCommands);

    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}
