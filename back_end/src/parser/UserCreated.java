/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose Store all of the user's created information, such as defined variables and user-defined methods.
 * @Dependencies exceptions
 * @Uses: Created in the CommandController class, but used in the Parser and also in Nodes that need  access to user-defined information
 */

package parser;

import exceptions.external.InvalidInputException;
import exceptions.InvalidVariableException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCreated {
    private Map<String, Double> myVariables;
    private Map<String, UserCommand> myCommands;

    /**
     * @purpose UserCreated constructor, which initializes the variable hashmap and the commands hashmap
     */
    public UserCreated() {
        myVariables = new HashMap<>();
        myCommands = new HashMap<>();
    }

    /**
     * @purpose allows things with access to UserCreated to add in a new variable
     * @param variableName the key for the map, also the variable name that the user inputs
     * @param variableValue the value for the map, also the value stored in the variable name
     */
    public void addVariable(String variableName, double variableValue) {
        System.out.println(variableName + ": " + variableValue);
        myVariables.put(variableName, variableValue);
    }

    /**
     * @purpose get the value of a key, allows nodes to access user created variables
     * @param key the variable name that map holds
     * @return the value stored in that variable
     * @throws InvalidInputException when the variable doesn't exist
     */
    public double getValue(String key) throws InvalidInputException {
        try {
            return myVariables.get(key);
        }
        catch (Exception e){
            throw new InvalidVariableException(key);
        }
    }

    /**
     * @purpose allows checking for whether or not a command actually exists
     * @param key the  command name to check for
     * @return true if the user has created the command and it exists
     */
    public boolean containsCommand(String key) {
        return myCommands.containsKey(key);
    }

    /**
     * @purpose add in a new user command so that the user can define their own commands
     * @param commandName the name with which the command can be called
     * @param varNames the names of all of the variables that the  user may use in the command
     * @param commandContents the  string representation of the command that the parser will parse when the command is called
     */
    public void addUserCommand(String commandName, List<String> varNames, String commandContents){
        UserCommand myCommand = new UserCommand(varNames,commandContents);
        for (String var: varNames)
            myVariables.put(var,0.0);
        myCommands.put(commandName,myCommand);
    }

    /**
     * @purpose when a method with variables is called, its variables are added to the variable map to prevent throwing incorrect exceptions
     * @param methodName the name of the method to get variables from
     * @param myVariableValues the values of all of the method's input variables
     */
    public void setUpLocalVariables(String methodName, List<Double> myVariableValues) {
        List<String> variableNames = myCommands.get(methodName).getMyVariableNames();
        for (int i = 0; i < variableNames.size(); i++){
            myVariables.put(variableNames.get(i), myVariableValues.get(i));
        }
    }

    /**
     * @purpose allows the parser to get the contents of the command behind called so that it can process it
     * @param currentValue the name of the method being called
     * @return the string representation of the command to be parsed
     */
    public String getCommand(String currentValue) {
        return myCommands.get(currentValue).getMyMethodContents();
    }
}
