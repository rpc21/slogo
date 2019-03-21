package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */

public class Repeat extends CommandNode {

    private UserCreated myUserCreatedItems;
    private static final String repeatVariable  = ":repcount";
    public Repeat(String a) {
        super(a);
    }
    public Repeat(String a, UserCreated user) {
        super(a);
       myUserCreatedItems = user;
       user.addVariable(repeatVariable,1.0);
    }
    /**
     * This CommandNode has two children - a list of initializer information and a list of commands to be run. The first
     * will include a limit for the number of times the second should be run. The second will be a list of commands
     * to be executed that number of times.
     * @return the result of last evaluated command
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        double ret = 0;
        double iter = 1;

        double numIterations = super.getChildren().get(0).evaluate(myVisCommands, myTurtles);
        CommandNode myListNode = super.getChildren().get(1);

        for (int i = 0; i < numIterations; i++){
            ret = myListNode.evaluate(myVisCommands,myTurtles);
            iter += 1;
            myUserCreatedItems.addVariable(repeatVariable,iter);
        }
        return ret;
    }
    /**
     * This CommandNode needs access to the UserCreated class in order to update ":repcount" at the end
     * of each iteration over the commands
     */
    @Override
    public boolean needsUserCreated(){ return true;}
}