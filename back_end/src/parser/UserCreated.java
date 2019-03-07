package parser;

import apis.AddVariable;
import apis.GetVariableValue;

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
    GetVariableValue myGetVarValFunction = new GetVariableValue() {
        @Override
        public double getUserVariableValue(String s) {return getVariableValue(s);}
    };
    public UserCreated() {
        myVariables = new HashMap<>();
        myCommands = new HashMap<>();
    }

    public AddVariable getMyAddVarFunction(){
        return myAddVarFunction;
    }
    public GetVariableValue getMyGetVarFunction() {return myGetVarValFunction;}

    public void addVariable(String variableName, double variableValue) {
        myVariables.put(variableName, variableValue);
    }

    public Map<String, Double> getVariableMap() {
        return Collections.unmodifiableMap(myVariables);
    }

    public double getVariableValue(String key) {
        return myVariables.get(key);
    }

    public void saveState() {

    }

    public void loadState(String fileLocation) {

    }
}
