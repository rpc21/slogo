package nodes.arithmetics;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;


public class Product extends CommandNode {
    public Product(String a) {
        super(a);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double firstExpression = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double secondExpression = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        return firstExpression * secondExpression;
    }



    @Override
    public void addChild(CommandNode c) {
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}