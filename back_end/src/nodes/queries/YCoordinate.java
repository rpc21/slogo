package nodes.queries;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class YCoordinate extends CommandNode {
    public YCoordinate(String n){
        super(n);
    }
    /**
     * @return active turtle's y-coordinate
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.getYCoor();
    }
}