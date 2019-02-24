package nodes;

public class MinusNode extends CommandNode {
    private static final int NO_INPUT = 0;
    public MinusNode(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate() {
        return -1.0 * super.getChildren().get(0).evaluate();
    }
    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}
