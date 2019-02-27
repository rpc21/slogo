package nodes;
import nodes.VisualCommand;

import java.util.List;


public class Random extends CommandNode {
    public Random(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands
    ) {
        double childValue = super.getChildren().get(0).evaluate(myVisCommands);
        return Math.floor(Math.random()*childValue) + 1;
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
