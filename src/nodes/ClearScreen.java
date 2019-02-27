package nodes;

import java.util.List;

public class ClearScreen extends CommandNode {
    public ClearScreen(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to get current coordinates to return distance moved to go home
     */
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        myVisCommands.add(new VisualClearScreen());
        return 0;
    }
    @Override
    public void addChild(CommandNode c){
        throw new IllegalArgumentException();
    }
}
