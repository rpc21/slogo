package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */

public class UserInstruction extends CommandNode {
    private UserCreated myUserCreatedItems;
    public UserInstruction(String n){
        super(n);
    }
    public UserInstruction(String n, UserCreated user){
        super(n);
        myUserCreatedItems = user;
    }
    /**
     * This CommandNode has three children - the method name associated with this user instruction, the list of values for
     * its parameters, and a list of commands to be executed. This method initializes each of the variable values
     * associated with this method and then evaluates the the list of accompanying commands
     * @return result of last command evaluated
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidInputException {
        String myUserMethodName = super.getChildren().get(0).toString();
        CommandNode listOfVariableValues = super.getChildren().get(1);
        List<Double> myVariableValues = new ArrayList<>();
        for (CommandNode c: listOfVariableValues.getChildren()) {
            myVariableValues.add(c.evaluate(myVisCommands, myTurtles));
        }
        myUserCreatedItems.setUpLocalVariables(myUserMethodName,myVariableValues);
        CommandNode listOfCommands = super.getChildren().get(2);
        return listOfCommands.evaluate(myVisCommands,myTurtles);
    }
    /**
     * This CommandNode is a method unique to the user definition
     */
    @Override
    public boolean isMethodDeclaration(){return true;}
    /**
     * This CommandNode needs access to the UserCreated class in order to initialize the variables local to this method
     */
    @Override
    public boolean needsUserCreated(){
        return true;
    }
}
