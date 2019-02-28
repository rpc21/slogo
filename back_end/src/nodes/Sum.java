package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;


public class Sum extends CommandNode {
    public Sum(String a){
        super(a);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        int sum = 0;
        for (CommandNode c: super.getChildren()) {
            sum += c.evaluate(myVisCommands, myTurtle);
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
