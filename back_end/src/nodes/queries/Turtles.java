package nodes.queries;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */

public class Turtles extends CommandNode {
    public Turtles(String n){
        super(n);
    }
    /**
     * @return number of turtles currently on the canvas
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.size();
    }
}
