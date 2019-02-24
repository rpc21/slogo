public class PiNode extends CommandNode {
    public PiNode(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate() {
        return Math.PI;
    }
    /**
     * Adds an addend to this SumNode's list of Children as Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 0)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}