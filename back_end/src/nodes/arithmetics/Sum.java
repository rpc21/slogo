package nodes.arithmetics;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;


public class Sum extends CommandNode {
    public Sum(String a){
        super(a);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        int sum = 0;
        for (CommandNode c: super.getChildren()) {
            sum += c.evaluate(myVisCommands, myTurtles);
        }
        return sum;
    }

    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}
