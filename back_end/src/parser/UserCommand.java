/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose parse the user's input and create CommandNodes for the controller to execute. Additionally, check the user's
 *          input and make sure it is all valid so that the code does not crash.
 * @Dependencies exceptions and nodes
 * @Uses: Used in the CommandController class, which takes in a string to parse and sends it over to the parser. This
 *        is the only instance of the parser.
 */

package parser;

import java.util.Collections;
import java.util.List;

public class UserCommand {
    private String myMethodContents;
    private List<String> myVariableNames;

    public UserCommand(List<String> inputVariables, String methodContents) {
        myVariableNames = Collections.unmodifiableList(inputVariables);
        myMethodContents = methodContents;
    }

    public String getMyMethodContents(){
        return myMethodContents;
    }

    public List<String> getMyVariableNames(){
        return Collections.unmodifiableList(myVariableNames);
    }

}
