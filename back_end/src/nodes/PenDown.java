package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class PenDown extends CommandNode {
    public PenDown(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        myVisCommands.add(new VisualPenDown());
        myTurtle.setPenState(false);
        return 1;
    }
}
