package nodes;

import apis.ImmutableVisualCommand;
import turtle.Bale;
import turtle.Turtle;

import java.util.List;

public class SetPosition extends TurtleCommand{
    private static final String methodName  = "setPosition";
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
        double oldXCoor = myTurtles.getLastActiveTurtle().getXCoor();
        double oldYCoor = myTurtles.getLastActiveTurtle().getYCoor();
        super.setMyTurtleCommands(methodName);
        super.invokeTurtles(new Object[]{newXCoor,newYCoor}, myTurtles);
        double distance = Math.pow(Math.abs(oldXCoor - newXCoor),2)  +
                Math.pow(Math.abs(oldYCoor - newYCoor),2);
        return distance;
    }
    @Override
    public void addChild(CommandNode c){
        if (super.getChildren().size() == 2)
            throw new IllegalArgumentException();
        super.addChild(c);
    }
}