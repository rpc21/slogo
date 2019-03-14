package nodes.structures;
import apis.ImmutableVisualCommand;
import exceptions.InvalidInputException;
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
        double ret = 0.0;
        for (CommandNode c : listOfCommands.getChildren())
            ret = c.evaluate(myVisCommands,myTurtles);
        return ret;
    }
    @Override
    public boolean isMethodDeclaration(){return true;}
    @Override
    public boolean needsUserCreated(){
        return true;
    }
}
