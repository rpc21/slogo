public class PowNode extends CommandNode {
    private static final int NO_INPUT = 0;
    private static double base;
    private static double exp;
    public PowNode(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate() {
        return Math.pow(base,exp);
    }
    /**
     * Adds an addend to this SumNode's list of Children as Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 0)
            base = c.evaluate();
        if (super.getChildren().size() == 1)
            exp = c.evaluate();
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
