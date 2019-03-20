package nodes.variables;
import apis.ImmutableVisualCommand;
import exceptions.InvalidVariableException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class Variable extends CommandNode {
    private final String myVarName;
    private double myValue;
    private static final double NOT_ASSIGNED = 0;
    private UserCreated myUserCreated;
    public Variable(String variableName){
        super(variableName);
        myVarName = variableName;
        myValue = NOT_ASSIGNED;
    }
    public Variable(String variableName, UserCreated userCreated) {
        super(variableName);
        myVarName = variableName;
        myUserCreated =  userCreated;
    }
    /**
     * This is a variable node that is meant to get the current value associated with the variable name. This value
     * could not be parsed for as the value of variables may change throughout the process of evaluating a a command tree
     */
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) throws InvalidVariableException {
        try {
            myValue = myUserCreated.getValue(myVarName);
        }
        catch(Exception e) {
            throw new InvalidVariableException(myVarName);
        }
        return myValue;
    }

}
