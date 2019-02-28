package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class SetHeading extends CommandNode {
    public SetHeading(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to calculate degrees moved, rather than the new heading
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands, myTurtle);
        VisualTurtleHeading myHeading = new VisualTurtleHeading(degrees);
        myVisCommands.add(new VisualTurtleHeading(degrees));
        return Math.abs(myTurtle.getHeading() - degrees);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
