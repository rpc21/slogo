package nodes.queries;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class GetPenColor extends CommandNode {
    public GetPenColor(String n){
        super(n);
    }
    /**
     * @return active turtle's pen color index
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myTurtles.getPenColor();
    }
}
