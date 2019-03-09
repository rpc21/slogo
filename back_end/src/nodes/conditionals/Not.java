package nodes.conditionals;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;

import turtle.Bale;

import java.util.List;


public class Not extends BooleanNode {

    public Not(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        if (super.getFirstExpression(myVisCommands, myTurtles) == ZERO) {
            return ONE;
        }
        return ZERO;
    }


    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}