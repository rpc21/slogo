package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class Backward extends CommandNode {
    public Backward(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle)  {
        double pixels = myChildren.get(0).evaluate(myVisCommands, myTurtle);
        myVisCommands.add(new VisualTurtleForward( -1.0 * pixels));
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
