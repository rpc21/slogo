package nodes;

public class PowNode extends CommandNode {
    public PowNode(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate() {
        double base = super.getChildren().get(0).evaluate();
        double exp = super.getChildren().get(1).evaluate();
        return Math.pow(base,exp);
    }
    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
