package nodes;

public class SumNode extends CommandNode {
    public SumNode(String a){
        super(a);
    }
    @Override
    public double evaluate() {
        int sum = 0;
        for (CommandNode c: super.getChildren()) {
            sum += c.evaluate();
        }
        return sum;
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
