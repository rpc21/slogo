package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualBackgroundColor;
import turtle.Bale;

import java.util.List;

public class SetBackground extends CommandNode {
    public SetBackground(String n){
        super(n);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        int index = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        myVisCommands.add(new VisualBackgroundColor(index));
        return index;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==1) {
            throw new IllegalArgumentException();
        }
        super.addChild(c);
    }
}
