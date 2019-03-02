


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
        myTurtle = new Turtle();
        myVisualCommands = new ArrayList<>();
    }

    public double execute(String command, String language) throws InvalidCommandException, NothingToRunException {
        updateLanguage(language);
        CommandNode myNode;
        double ret = 0;
        myVisualCommands.clear();
        try {
            for (CommandNode a: myParser.parse(command)) {
                ret = a.evaluate(myVisualCommands,myTurtle);
            }
            //myNode = myParser.parse(command).get(0); // note of change! This is changed now because their could be many commands in a list that come from a parser.
        } catch (IndexOutOfBoundsException e) {
            throw new NothingToRunException();
        }
        return ret;
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
