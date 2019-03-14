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
public class Home extends TurtleCommand {
    public Home(String name){
        super(name);
    }
    private static final String methodName= "goHome";
    /**
     * Stores active turtle's x-coordinate and y-coordinate.
     * Sets associated method name for myTurtles to handle as "goHome", which handles setting the turtles' positions
     * and headings, and adding the associated visual commands
     * @return distance from the previous active turtle's location to the origin
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double currXCoor = myTurtles.getXCoor();
        double currYCoor = myTurtles.getYCoor();
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{},myTurtles));
        return Math.sqrt(Math.pow(currXCoor,2) + Math.pow(currYCoor,2));
    }
}
