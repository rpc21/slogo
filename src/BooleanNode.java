public abstract class BooleanNode extends CommandNode {
    private static final int INVALID_INPUT = 0;

    public BooleanNode(String a) {
        super(a);
    }
    protected double getFirstExpression(){
        return super.getChildren().get(0).evaluate();
    }
    protected double getSecondExpression(){
        return super.getChildren().get(1).evaluate();
    }

    @Override
    public abstract double evaluate();

    /**
     * Adds an addend to this SumNode's list of Children as Parser reads them in
     *
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c) {
        super.addChild(c);
    }
}
