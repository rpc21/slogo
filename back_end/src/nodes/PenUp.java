package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class PenUp extends CommandNode {
    public PenUp(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        myVisCommands.add(new VisualPenUp());
        myTurtles.get(0).setPenState(true);
        return 0;
    }
}
