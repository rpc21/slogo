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
public class DoTimes extends CommandNode {

    private UserCreated myUserCreatedItems;
    public DoTimes(String a) {
        super(a);
    }
    public DoTimes(String a, UserCreated user) {
        super(a);
        myUserCreatedItems = user;
    }
    /**
     * This CommandNode has two children - a list of initializer information and a list of commands to be run. The first
     * will be a variable that begins at 1 and runs up to and includes the limit. The second will be a list of commands
     * to be executed that number of times.
     * @return the result of last evaluated command
     */

    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
    //initialize specific variable name and update with each iteration
        double ret = 0;
        CommandNode myVariable = super.getChildren().get(0);
        CommandNode myCommands = super.getChildren().get(1);

        String varName = myVariable.getChildren().get(0).toString();
        double numIterations = myVariable.getChildren().get(1).evaluate(myVisCommands, myTurtles);
        myUserCreatedItems.addVariable(varName,1.0);


        for (int iter = 0; iter <= numIterations; iter++){
            ret = myCommands.evaluate(myVisCommands,myTurtles);
            myUserCreatedItems.addVariable(varName,iter + 2.0);
        }
        return ret;
    }
    /**
     * This CommandNode needs access to the UserCreated class in order to update the user's defined variable at the end
     * of each iteration over the commands
     */
    @Override
    public boolean needsUserCreated(){ return true;}
}