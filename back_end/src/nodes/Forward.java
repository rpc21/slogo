package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class Forward extends CommandNode {
    public Forward(String name){
        super(name);
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        double pixels = myChildren.get(0).evaluate(myVisCommands, myTurtle);
        VisualTurtleMove myVisMove = new VisualTurtleMove( 1.0 * pixels, myTurtle.getHeading());
        myVisCommands.add(myVisMove);
        myTurtle.setXCoor(myTurtle.getXCoor() + myVisMove.getXDelta());
        myTurtle.setYCoor(myTurtle.getYCoor() + myVisMove.getYDelta());
        return pixels;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 1)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
