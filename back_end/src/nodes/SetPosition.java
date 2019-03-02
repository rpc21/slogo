package nodes;

import apis.ImmutableVisualCommand;
import turtle.Turtle;

import java.util.List;

public class SetPosition extends CommandNode {
    public SetPosition(String name){
        super(name);
    }
    /**
     * TODO - Use immutable turtle state to get current coordinates to determine degrees turned
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Turtle myTurtle) {
        double newXCoor = super.getChildren().get(0).evaluate(myVisCommands, myTurtle);
        double newYCoor = super.getChildren().get(1).evaluate(myVisCommands, myTurtle);
        myVisCommands.add(new VisualTurtlePosition(newXCoor,newYCoor));
        double distance = Math.pow(Math.abs(myTurtle.getXCoor() - newXCoor),2)  + Math.pow(Math.abs(myTurtle.getYCoor() - newYCoor),2);
        myTurtle.setXCoor(newXCoor);
        myTurtle.setYCoor(newYCoor);
        return distance;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}