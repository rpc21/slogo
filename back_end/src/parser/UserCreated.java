package parser;

import apis.AddVariable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserCreated {
    Map<String, Double> myVariables;
    Map<String, UserCommand> myCommands;

    AddVariable myAddVarFunction = new AddVariable() {
        @Override
        public void addVar(String s,Double d) {
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
        myVariables.put(variableName, variableValue);
        for (String a: myVariables.keySet())
            System.out.println(a + ": "+  myVariables.get(a));

    }

    public Map<String, Double> getVariableMap() {
        return Collections.unmodifiableMap(myVariables);
    }
}
