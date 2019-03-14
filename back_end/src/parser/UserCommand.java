package parser;

import java.util.List;

public class UserCommand {
    private String myMethodContents;
    private List<String> myVariableNames;

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
