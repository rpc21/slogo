package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class ListNode extends CommandNode {
    public ListNode(String a){
        super(a);
    }
    @Override
    public double evaluate(java.util.List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double ret = 0;
        for (CommandNode c: super.getChildren()) {
            ret = c.evaluate(myVisCommands, myTurtles);
        }
       return ret;
    }
}
