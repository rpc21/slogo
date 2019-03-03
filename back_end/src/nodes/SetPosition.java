package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
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
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double newXCoor = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double newYCoor = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        myVisCommands.add(new VisualTurtlePosition(newXCoor,newYCoor));
        double distance = Math.pow(Math.abs(myTurtles.get(0).getXCoor() - newXCoor),2)  + Math.pow(Math.abs(myTurtles.get(0).getYCoor() - newYCoor),2);
        for (Turtle t: myTurtles.getActiveTurtles()) {
            t.setXCoor(newXCoor);
            t.setYCoor(newYCoor);
        }
        return distance;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}