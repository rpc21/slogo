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
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            myTurtles.get(id).setPenState(false);
            myVisCommands.add(new VisualPenDown(id));
        }
        return 1;
    }
}
