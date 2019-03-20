package nodes.queries;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class XCoordinate extends CommandNode {
    public XCoordinate(String n){
        super(n);
    }
    /**
     * @return active turtle's x-coordinate
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.getXCoor();
    }
}
