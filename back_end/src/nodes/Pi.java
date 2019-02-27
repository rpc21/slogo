package nodes;

import java.util.List;


public class Pi extends CommandNode {
    public Pi(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        return Math.PI;
    }
    /**
     * Adds an addend to this nodes.SumNode's list of Children as main.Parser reads them in
     * @TODO Read in possible Argument issues from a resources file to ensure parameter specifications are satisfied
     */
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 0)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}