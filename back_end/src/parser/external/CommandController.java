package parser.external;

import apis.ImmutableVisualCommand;
import exceptions.*;
import nodes.CommandNode;
import parser.Parser;
import parser.UserCreated;
import turtle.Bale;
import turtle.ImmutableTurtleState;
import turtle.Turtle;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CommandController {
    private Parser myParser;
    private Bale myTurtles;
    List<ImmutableVisualCommand> myVisualCommands;
    private UserCreated myUserCreated;


    public CommandController() {
        myUserCreated = new UserCreated();
        myParser = new Parser(myUserCreated);
        myTurtles = new Bale();
        myTurtles.add(new Turtle(0));
        myVisualCommands = new ArrayList<>();
    }

    public double execute(String command, String language) throws InvalidInputException {
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

