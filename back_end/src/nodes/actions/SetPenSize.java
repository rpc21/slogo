package nodes.actions;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.TurtleCommand;
import turtle.Bale;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class SetPenSize extends TurtleCommand {
    private static final String methodName = "setPenSize";
    public SetPenSize(String n){
        super(n);
    }
    /**
     * Evaluates child node to get pixels of new pen size before invoking Turtles to use "setPenSize" to invoke
     * appropriate turtles to accommodate this change and add associated visual commands
     * @return size of new pen
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double pixels = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        return super.invokeTurtles(pixels,methodName,myVisCommands,myTurtles);
    }

}
