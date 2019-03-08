package parser;

import exceptions.InvalidInputException;
import exceptions.InvalidVariableException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCreated {
    Map<String, Double> myVariables;
    Map<String, UserCommand> myCommands;

    public UserCreated() {
        myVariables = new HashMap<>();
        myCommands = new HashMap<>();
    }

    public void addVariable(String variableName, double variableValue) {
        myVariables.put(variableName, variableValue);
    }

    public double getVariableValue(String key) {
        return myVariables.get(key);
    }

    public double getValue(String key) throws InvalidInputException {
        try {
            return myVariables.get(key);
        }
        catch (Exception e){
            throw new InvalidVariableException(key);
        }
    }

    public void addUserCommand(String commandName, List<String> varNames, String commandContents){
        UserCommand myCommand = new UserCommand(varNames,commandContents);
        myCommands.put(commandName,myCommand);
    }
}
