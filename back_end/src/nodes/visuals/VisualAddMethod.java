package nodes.visuals;

import apis.ImmutableVisualCommand;
import apis.VisualUpdateAPI;

import java.util.List;

public class VisualAddMethod implements ImmutableVisualCommand {
    private String myMethodName;
    private List<String> myVariableNames;
    public VisualAddMethod(String methodName, List<String> varNames){
        myMethodName = methodName;
        myVariableNames = varNames;
    }
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.addMethod(myMethodName,myVariableNames);
    }
}
