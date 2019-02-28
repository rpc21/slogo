package parser;

import java.util.Collections;
import java.util.HashMap;
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

    public Map<String, Double> getVariableMap() {
        return Collections.unmodifiableMap(myVariables);
    }
}
