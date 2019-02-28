


package parser.external;
import java.util.ArrayList;
import java.util.List;

import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.NothingToRunException;
import nodes.CommandNode;
import parser.Parser;
import turtle.ImmutableTurtleState;
import turtle.Turtle;

public class CommandController {
    private Parser myParser;
    private Turtle myTurtle;
    private static final int INVALID_COMMAND = 0;
    List<ImmutableVisualCommand> myVisualCommands;

    public CommandController(){
        myParser = new Parser();
    }

    public double execute(String command) throws InvalidCommandException, NothingToRunException {
        CommandNode myNode;
        myVisualCommands = new ArrayList<>();
        try {
            myNode = myParser.parse(command).get(0); // note of change! This is changed now because their could be many commands in a list that come from a parser.
        } catch (IndexOutOfBoundsException e) {
            throw new NothingToRunException();
        }
        return myNode.evaluate(myVisualCommands, myTurtle);
    }
    public List<ImmutableVisualCommand> getMyVisualCommands(){
        return myVisualCommands;
    }
    public void updateTurtle(ImmutableTurtleState currentState) {

    }

    public void updateLanguage(String newLanguage) {
        myParser.updateLanguage(newLanguage);
    }

}
