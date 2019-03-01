package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class SetTowards extends CommandNode {
    public SetTowards(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to get current coordinates to determine degrees turned
     */

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        double newXCoor = super.getChildren().get(0).evaluate(myVisCommands, myTurtle);
        double newYCoor = super.getChildren().get(1).evaluate(myVisCommands, myTurtle);
        var angle = Math.atan2(newYCoor - myTurtle.getYCoor(), newXCoor - myTurtle.getXCoor() );
        angle = angle * (180/Math.PI);
        if(angle < 0)
        {
            angle = 360 - (-1.0 * angle);
        }
        var newAngle = 90 + angle;
        myVisCommands.add(new VisualTurtleTurn(newAngle));
        return Math.abs(myTurtle.getHeading() - newAngle);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
