package nodes.arithmetics;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;


public class Quotient extends CommandNode {

    public Quotient(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        if (validDenominator(secondExpression)) {
            return firstExpression / secondExpression;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private boolean validDenominator(double d){
        return d == 0.0;
    }


    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}