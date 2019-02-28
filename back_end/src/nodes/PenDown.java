package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class PenDown extends CommandNode {
    public PenDown(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        myVisCommands.add(new VisualPenDown());
        return 1;
    }
}
