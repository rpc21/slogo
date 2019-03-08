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

    public boolean containsCommand(String key) {
        return myCommands.containsKey(key);
    }

    public boolean containsVariable(String variable) {
        return myVariables.containsKey(variable);
    }

    public void addUserCommand(String commandName, List<String> varNames, String commandContents){
        UserCommand myCommand = new UserCommand(varNames,commandContents);
        for (String var: varNames)
            myVariables.put(var,0.0);
        myCommands.put(commandName,myCommand);
    }

    public void setUpLocalVariables(String methodName, List<Double> myVariableValues) {
        System.out.println(methodName);
        System.out.println(myCommands.get(methodName));
        List<String> variableNames = myCommands.get(methodName).getMyVariableNames();
        for (int i = 0; i < variableNames.size(); i++)
            myVariables.put(variableNames.get(i),myVariableValues.get(i));
    }

    public int getExpectedNumberOfParameters(String currentCommandKey) {
        return myCommands.get(currentCommandKey).getMyVariableNames().size();
    }

    public String getCommand(String currentValue) {
        return myCommands.get(currentValue).getMyMethodContents();
    }
}
