package nodes;

import nodes.VisualCommand;

import java.util.List;

public class TurtleLeft extends CommandNode {
    public TurtleLeft(String name){
        super(name);
    }
    @Override
    public double evaluate(List<VisualCommand> myVisCommands) {
        double pixels = myChildren.get(0).evaluate(myVisCommands);
        //myVisCommands.add(new VisualTurtleLeft(pixels));
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}