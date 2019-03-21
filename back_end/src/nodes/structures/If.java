package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */

public class If extends CommandNode {
    private static final int FALSE_PATH = 0;
    private static final int IF_STATEMENT= 0;
    private static final int TRUE_PATH = 1;

    public If(String a) {
        super(a);
    }
    /**
     * This CommandNode has two children - one is a conditional, and the second is a list of commands that are invoked
     * to execute if the conditional evaluates to true
     * @return the result of the last command run if the conditional evaluates to true, or 0 if the conditional is false
     *          or there are no commands to be run
     * @see ListNode
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double conditional = super.getChildren().get(IF_STATEMENT).evaluate(myVisCommands, myTurtles);
        if (conditional != 0){
            return super.getChildren().get(TRUE_PATH).evaluate(myVisCommands, myTurtles);
        }
        else{
            return FALSE_PATH;
        }
    }

}