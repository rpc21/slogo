package parser;

import apis.AddVariable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserCreated {
    Map<String, Double> myVariables = new HashMap<>();
    Map<String, UserCommand> myCommands = new HashMap<>();

    AddVariable myAddVarFunction = new AddVariable() {
        @Override
        public void addNewVariable(String s,Double d) {
            addVariable(s,d);
        }
    };
    public UserCreated() {
        myVariables = new HashMap<>();
        myCommands = new HashMap<>();
    }

    public AddVariable getMyAddVarFunction(){
        return myAddVarFunction;
    }

    public void addVariable(String variableName, double variableValue) {
        System.out.println("REFERENCED ADD VAR FUNCTION");
        myVariables.put(variableName, variableValue);
        for (String a: myVariables.keySet())
            System.out.println(a + ": "+  myVariables.get(a));

    }

    public Map<String, Double> getVariableMap() {
        return Collections.unmodifiableMap(myVariables);
    }

    public double getVariableValue(String key) {
        return myVariables.get(key);
    }
}
