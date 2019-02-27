package nodes;

import java.util.List;

public class SetTowards extends CommandNode {
    public SetTowards(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to get current coordinates to determine degrees turned
     */
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        double newXCoor = super.getChildren().get(0).evaluate(myVisCommands);
        double newYCoor = super.getChildren().get(1).evaluate(myVisCommands);
        return Math.pow(newXCoor,2) + Math.pow(newYCoor,2);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
