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
public class SetPenColor extends TurtleCommand {
    private static final String methodName = "setPenColor";
    public SetPenColor(String n){
        super(n);
    }
    /**
     * Evaluates child node to get index of new pen color before invoking Turtles to use "setPenColor" to invoke
     * appropriate turtles to accommodate this change and add associated visual commands
     * @return index of new pen color
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double index = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        super.setMyTurtleCommands(methodName);
        myVisCommands.addAll(super.invokeTurtles(new Object[]{index},myTurtles));
        return index;
    }

}
