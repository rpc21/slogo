package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class SetPenColor extends CommandNode {
    public SetPenColor(String n){
        super(n);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        int index = (int)super.getChildren().get(0).evaluate(myVisCommands, myTurtle);
        myTurtle.setPenColor(index);
        myVisCommands.add(new VisualBackgroundColor(index));
        return index;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() ==1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }

}
