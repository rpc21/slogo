package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;


public class Remainder extends CommandNode {
    public Remainder(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands, myTurtle);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands, myTurtle);
        return firstExpression % secondExpression;
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