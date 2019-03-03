package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class PenDown extends CommandNode {
    public PenDown(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        myVisCommands.add(new VisualPenDown());
        myTurtles.get(0).setPenState(false);
        return 1;
    }
}
