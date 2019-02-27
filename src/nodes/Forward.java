package nodes;

import nodes.VisualCommand;

import java.util.List;

public class Forward extends CommandNode {
    public Forward(String name){
        super(name);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        double pixels = myChildren.get(0).evaluate(myVisCommands);
        myVisCommands.add(new VisualTurtleForward(pixels));
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
