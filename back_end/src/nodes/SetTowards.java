package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;

import java.util.List;

public class SetTowards extends CommandNode {
    public SetTowards(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to get current coordinates to determine degrees turned
     */

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double deltaX = super.getChildren().get(0).evaluate(myVisCommands, myTurtles) - myTurtles.get(0).getXCoor();
        double deltaY = super.getChildren().get(1).evaluate(myVisCommands, myTurtles) - myTurtles.get(0).getYCoor();
        double angle = Math.atan2( deltaY,deltaX );
        System.out.println("DELTAX: " + deltaX);
        System.out.println("DELTAY: " + deltaY);
        double degrees = Math.toDegrees(angle);
        System.out.println("DEGREE TURN PRETRANSFORMATION: " + degrees);
//        if (deltaX > 0){
//            //do nothing
//        }
//        else if (deltaY >= 0 & deltaX < 0){
//            degrees = 450 - degrees;
//        }
//        else if (deltaY < 0 & deltaX < 0){
//            degrees = Math.abs(-90 + degrees);
//        }
//        else if (deltaY > 0 & deltaX == 0)
//            degrees = 180;
//        else if (deltaY < 0 & deltaX == 0)
//            degrees = -180;
//        else
//             degrees = 0;

        if (Math.signum(deltaX) > 0 & Math.signum(deltaY) > 0)
            degrees = 90 - degrees;
        else if (Math.signum(deltaX) > 0 & Math.signum(deltaY) < 0) {
            degrees = Math.abs(degrees) + 90;
        }
        else if (Math.signum(deltaX) < 0 & Math.signum(deltaY) < 0)
            degrees = Math.abs(-90 + degrees);
        else
            degrees = -1.0 * (180 - degrees);
        System.out.println("DEGREE TURN POST: " + degrees);
        System.out.println("CURRENT HEADING: " + myTurtles.get(0).getHeading());
        myVisCommands.add(new VisualTurtleTurn(degrees - myTurtles.get(0).getHeading()));
        myTurtles.get(0).updateHeading(degrees - myTurtles.get(0).getHeading());
        double curr = myTurtles.get(0).getHeading();
        if (curr > 180)
            curr = 360 - curr;
        return Math.abs(curr  - angle);
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}
