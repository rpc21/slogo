/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose Control the parser and execute commands. Additionally, create UserCreated to be used throughout the back-end.
 *          Executes both visual and non-visual commands.
 * @Dependencies ImmutableVisualCommand, exceptions, nodes, parser, and turtle objects.
 * @Uses: This is the one back-end class that the front-end instantiates. This class acts as the bridge between front
 *        and back-end in order to facilitate communication.
 */

package parser.external;

import apis.ImmutableVisualCommand;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import parser.Parser;
import parser.UserCreated;
import turtle.Bale;
import turtle.Turtle;
import java.util.ArrayList;
import java.util.List;

public class CommandController {
    private Parser myParser;
    private Bale myTurtles;
    List<ImmutableVisualCommand> myVisualCommands;
    private UserCreated myUserCreated;


    /**
     * @purpose constructor for the CommandController so that front-end can use it. Creates a new usercreated,
     *          initiates list of turtles, and sets up parser and visual commands.
     */
    public CommandController() {
        myUserCreated = new UserCreated();
        myParser = new Parser(myUserCreated);
        myTurtles = new Bale();
        myTurtles.add(new Turtle(0));
        myVisualCommands = new ArrayList<>();
    }

    /**
     * @purpose Execute the commands input by the user. First, parses the commands and then runs through and executes all of them.
     * @param command The user's input string to be parsed
     * @param language The current language that is selected in the front-end.
     * @return the return value of the final command in the sequence.
     * @throws InvalidInputException when the user inputs something invalid, such as a variable or command.
     */
    public double execute(String command, String language) throws InvalidInputException {
        myParser.updateLanguage(language);
        double ret = 0;
        myVisualCommands.clear();
        for (CommandNode node : myParser.parse(command)) {
            ret = node.evaluate(myVisualCommands, myTurtles);
        }
        return ret;
    }

    /**
     * @purpose give front-end access to an immutable list of commands so that it can execute visual commands without
     *          needing information from the back-end
     * @return a list of current immutable visual commands that need  to be run
     */
    public List<ImmutableVisualCommand> getMyVisualCommands(){
        return myVisualCommands;
    }

}

