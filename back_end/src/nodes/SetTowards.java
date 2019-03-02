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
        double angle = Math.atan2(newYCoor - myTurtle.getYCoor(), newXCoor - myTurtle.getXCoor() );
        System.out.println("RESULT OF ATAN: " + angle * (180.0/Math.PI));
        System.out.println("CURRENT HEADING: " + myTurtle.getHeading());
        angle = angle * (180.0/Math.PI);
        myTurtle.updateHeading(angle);
//        angle = angle * (180/Math.PI);
//        if(angle < 0)
//        {
//            angle = 360 - (-1.0 * angle);
//        }
//        var newAngle = 90 + angle;
        if (myTurtle.getXCoor() - newXCoor == 0)
            angle = 180.0;
        System.out.println(angle);
        myVisCommands.add(new VisualTurtleTurn(angle));
        return Math.abs(myTurtle.getHeading() - angle);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
