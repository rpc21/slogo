package nodes;

import java.util.List;

public class ShowTurtle extends CommandNode {
    public ShowTurtle(String name){
        super(name);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        myVisCommands.add(new VisualShowTurtle());
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
