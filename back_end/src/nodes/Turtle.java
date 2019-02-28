package nodes;

import apis.ImmutableVisualCommand;

import java.util.List;

public class Turtle extends CommandNode {
    public Turtle(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands) {
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