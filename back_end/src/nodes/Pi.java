package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;


public class Pi extends CommandNode {
    public Pi(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return Math.PI;
    }

    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 0) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }

}