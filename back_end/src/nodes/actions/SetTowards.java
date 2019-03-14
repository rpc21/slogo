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
public class SetTowards extends TurtleCommand {
    private static final String methodName = "setTowards";
    public SetTowards(String name){
        super(name);
    }
    /**
     * Evaluates children node to get x-coordinate and y-coordinate appropriate turtles will be facing and
     * requests current heading of the last turtle that will change.Invokes myTurtles to tell appropriate turtles to
     * set their heading towards the new (x,y) position using "setTowards", along with the appropriate visual commands
     * @return distance turned of last turtle involved in change
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double towardsX=  super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        double towardsY =  super.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        double currHeading = myTurtles.getLastActiveTurtle().getHeading();
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{towardsX,towardsY},myTurtles));

        double newHeading = myTurtles.getHeading();
        return Math.abs(currHeading - newHeading);
    }
}
