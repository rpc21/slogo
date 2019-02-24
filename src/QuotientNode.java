public class QuotientNode extends CommandNode {
    private static final int INVALID_INPUT = 0;

    public QuotientNode(String a) {
        super(a);
    }

    @Override
    public double evaluate() {
        double firstExpression = super.getChildren().get(0).evaluate();
        double secondExpression = super.getChildren().get(1).evaluate();
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
     * Adds an addend to this SumNode's list of Children as Parser reads them in
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