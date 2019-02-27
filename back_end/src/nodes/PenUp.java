package nodes;

import java.util.List;

public class PenUp extends CommandNode {
    public PenUp(String name){
        super(name);
    }

    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        myVisCommands.add(new VisualPenUp());
        return 0;
    }
}
