package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;


public class Power extends CommandNode {
    public Power(String commandName) {
        super(commandName);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double base = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double exp = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        return Math.pow(base,exp);
    }

    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}
