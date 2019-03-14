package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class SetPosition extends TurtleCommand {
    private static final String methodName  = "setPosition";
    public SetPosition(String name){
        super(name);
    }
    /**
     * Evaluates children node to get new x-coordinate and y-coordinate and requests position of last turtle that
     * will change before invoking turtles to invoke appropriate turtles with "setPosition" to make changes and add in
     * visual commands
     * @return distance moved of last turtle that was set to move to the new position
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double newXCoor = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double newYCoor = super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        double oldXCoor = myTurtles.getLastActiveTurtle().getXCoor();
        double oldYCoor = myTurtles.getLastActiveTurtle().getYCoor();

        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{newXCoor,newYCoor}, myTurtles));

        return Math.pow(Math.abs(oldXCoor - newXCoor),2)  +
                Math.pow(Math.abs(oldYCoor - newYCoor),2);
    }
}