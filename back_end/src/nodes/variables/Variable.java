package nodes.variables;

import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
import exceptions.InvalidVariableException;
import nodes.CommandNode;
import parser.UserCreated;
import turtle.Bale;

import java.util.List;

public class Variable extends CommandNode {
    private String myVarName;
    private double myValue;
    private static final double NOT_ASSIGNED = 0;
    private static final String NOT_ASSIGNED_ERROR = "WARNING: VARIABLE DOES NOT EXIST";
    public Variable(String variableName){
        super(variableName);
        myVarName = variableName;
        myValue = NOT_ASSIGNED;
    }
    public Variable(String variableName, UserCreated myUserCreated) throws InvalidInputException {
        super(variableName);
        myVarName = variableName;
        try {
            myValue = myUserCreated.getValue(myVarName);
        }
        catch(Exception e) {
            throw new InvalidVariableException(variableName);
        }
    }
    @Override
    public double evaluate(List<ImmutableVisualCommand> myVisCommands, Bale myTurtles) {
        return myValue;
    }

//    @Override
}
