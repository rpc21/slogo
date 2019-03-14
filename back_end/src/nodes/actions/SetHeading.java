
package nodes.actions;

import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class SetHeading extends TurtleCommand {
    private static final String methodName = "setHeading";
    public SetHeading(String name){
        super(name);
    }
    /**
     * Evaluates child node to get the new heading associated with right turn, and requests heading
     * of last turtle that will change. Prompts turtles to add the appropriate visual commands with
     * method "setHeading"
     * @return degrees turned
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double degrees = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double currHeading = myTurtles.getLastActiveTurtle().getHeading();
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{degrees},myTurtles));
        return Math.abs(currHeading - degrees);
    }
}
