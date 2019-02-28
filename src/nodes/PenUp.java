package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class PenUp extends CommandNode {
    public PenUp(String name){
        super(name);
    }

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        myVisCommands.add(new VisualPenUp());
        return 0;
    }
}
