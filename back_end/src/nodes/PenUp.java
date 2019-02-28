package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class PenUp extends CommandNode {
    public PenUp(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        myVisCommands.add(new VisualPenUp());
        myTurtle.setPenState(true);
        return 0;
    }
}
