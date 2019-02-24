public class TanNode extends CommandNode {
    private static final int NO_INPUT = 0;
    public TanNode(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate() {
        for (CommandNode c: super.getChildren()) {
            try {
                return Math.tan(c.evaluate());
            }
            catch(IllegalArgumentException e){
                return NO_INPUT;
            }
        }
        return NO_INPUT;
    }
    /**
     * Adds an addend to this SumNode's list of Children as Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}