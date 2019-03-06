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
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            myTurtles.get(id).setPenState(true);
            myVisCommands.add(new VisualPenUp(id));
        }
        return 1;
    }
}
