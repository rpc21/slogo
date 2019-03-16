package nodes.visuals;
import apis.ImmutableVisualCommand;
import apis.VisualUpdateAPI;

import java.util.List;
/**
 * @author Anna Darwish
 * @version 3/13/2019
 */
public class VisualAddMethod implements ImmutableVisualCommand {
    private final String myMethodName;
    private final List<String> myVariableNames;
    public VisualAddMethod(String methodName, List<String> varNames){
        myMethodName = methodName;
        myVariableNames = varNames;
    }
    /**
     * Invokes canvas to display new user created method and its arguments
     */
    @Override
    public void execute(VisualUpdateAPI myCanvas) {
        myCanvas.addMethod(myMethodName,myVariableNames);
    }
}
