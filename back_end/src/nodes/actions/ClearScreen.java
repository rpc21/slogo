
package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.visuals.VisualClearScreen;
import turtle.Bale;
import turtle.Turtle;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class ClearScreen extends CommandNode {
    public ClearScreen(String name){
        super(name);
    }
    /**
     * Stores active turtle's x-coordinate and y-coordinate before clearing myTurtles and creating a new turtle
     * with default parameters. Adds a visual command to clear the screen
     * @see VisualClearScreen
     * @return distance from the previous active turtle's location to the origin
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        double currXCoor = myTurtles.getXCoor();
        double currYCoor = myTurtles.getYCoor();
        myTurtles.clear();
        myTurtles.add(new Turtle(0));
        myVisCommands.add(new VisualClearScreen());
        return Math.sqrt(Math.pow(currXCoor,2) + Math.pow(currYCoor,2));
    }
}
