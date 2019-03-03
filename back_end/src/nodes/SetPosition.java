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
        double distance = 0;
        for (Integer id: myTurtles.getActiveTurtlesIDs()) {
            myVisCommands.add(new VisualTurtlePosition(id,newXCoor,newYCoor));
            distance = Math.pow(Math.abs(myTurtles.get(0).getXCoor() - newXCoor),2)  + Math.pow(Math.abs(myTurtles.get(0).getYCoor() - newYCoor),2);
            myTurtles.get(id).setXCoor(newXCoor);
            myTurtles.get(id).setYCoor(newYCoor);
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