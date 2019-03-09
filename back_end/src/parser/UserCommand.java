package parser;

import java.util.List;
import java.util.Map;

public class UserCommand {
    String myMethodContents;
    List<String> myVariableNames;

    public UserCommand(List<String> inputVariables, String methodContents) {
        myVariableNames = inputVariables;
        myMethodContents = methodContents;
    }

    public String getMyMethodContents(){
        return myMethodContents;
    }

    public List<String> getMyVariableNames(){
        return myVariableNames;
    }

}
