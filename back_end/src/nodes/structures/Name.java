package nodes.structures;
import apis.ImmutableVisualCommand;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Name extends CommandNode {
    private static final int NO_EXECUTE_RESULT = 0;
    public Name(String n){
        super(n);
    }
    /**
     * This CommandNode is used as a child of any CommandNode that requires a name (such as a variable) and is not
     * part of the set of current user instructions
     * @return 0
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles){
        return NO_EXECUTE_RESULT;
    }
}
