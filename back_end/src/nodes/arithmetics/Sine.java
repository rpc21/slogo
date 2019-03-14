package nodes.arithmetics;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;

import java.util.List;


public class Sine extends CommandNode {
    public Sine(String commandName) {
        super(commandName);
    }
        @Override
        public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
            return Math.sin(super.getChildren().get(0).evaluate(myVisCommands, myTurtles));
        }
        @Override
        public void addChild(CommandNode c){
            if (super.getChildren().size() == 1) {
                throw new IllegalArgumentException();
            }
            super.addChild(c);
        }
}

