


package parser.external;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import apis.ImmutableVisualCommand;
import exceptions.InvalidCommandException;
import exceptions.InvalidListException;
import exceptions.InvalidVariableException;
import exceptions.NothingToRunException;
import nodes.CommandNode;
import parser.Parser;
import parser.UserCreated;
import turtle.Bale;
import turtle.ImmutableTurtleState;
import turtle.Turtle;

public class CommandController {
    private Parser myParser;
    private Bale myTurtles;
    private static final int INVALID_COMMAND = 0;
    List<ImmutableVisualCommand> myVisualCommands;
    private UserCreated myUserCreated;


    public CommandController() {
        myUserCreated = new UserCreated();
        myParser = new Parser(myUserCreated);
        myTurtles = new Bale();
        myTurtles.add(new Turtle(0));
        myVisualCommands = new ArrayList<>();
    }

    public double execute(String command, String language) throws InvalidCommandException, NothingToRunException, InvalidVariableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, InvalidListException {
        updateLanguage(language);
        CommandNode myNode;
        double ret = 0;
        myVisualCommands.clear();
        for (CommandNode a : myParser.parse(command)) {
            ret = a.evaluate(myVisualCommands, myTurtles);
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
