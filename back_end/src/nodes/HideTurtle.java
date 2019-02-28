package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class HideTurtle extends CommandNode {
    public HideTurtle(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
        myVisCommands.add(new VisualHideTurtle());
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
