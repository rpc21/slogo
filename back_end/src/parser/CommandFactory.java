/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose Used by the parser to create CommandNodes without the parser having to know what kind of node to create.
 * @Dependencies exceptions and nodes, also uses reflection
 * @Uses: Used solely in the Parser class to create nodes.
 */

package parser;

import exceptions.InvalidCommandException;
import exceptions.external.InvalidInputException;
import nodes.CommandNode;
import nodes.variables.ConstantNode;
import nodes.structures.Name;
import nodes.variables.Variable;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

public class CommandFactory {
    private static final String PROPERTIES_LOCATION =  "parser/PackageLocation";
    private ResourceBundle myResources;

    public CommandFactory() {
        myResources = ResourceBundle.getBundle(PROPERTIES_LOCATION);
    }

    /**
     * @purpose make a CommandNode from solely a string so that the parser does not need to do logic to figure out which
     *          kind of node to use. Uses reflection to avoid a long hierarchy of if trees. The purpose of the properties
     *          file is so that nodes could be organized into packages, so not every node is in the same location.
     * @param c The string representation of the node to be created. This is the key in all of the properties files, and
     *          corresponds to the name of the node to be created in the nodes package.
     * @param userCreated The usercreated object that is held by Parser and created by CommandController. This is necessary
     *                    to pass in for the commands that modify variables or define methods. This is only used if they need
     *                    access to usercreated.
     * @return The CommandNode that is created by reflection.
     * @throws InvalidInputException if the command does not exist. This catches all issues that may come reflection
     *                               or from missing commands in the properties file.
     */
    public CommandNode makeCommand(String c, UserCreated userCreated) throws InvalidInputException {
        try {
            Class commandClass = Class.forName(myResources.getString(c));
            Constructor constructor = commandClass.getConstructor(String.class);
            CommandNode commandNode = (CommandNode) constructor.newInstance(c);
            if (commandNode.needsUserCreated()) {
                constructor = commandClass.getConstructor(String.class, UserCreated.class);
                commandNode = (CommandNode) constructor.newInstance(c, userCreated);
            }
            return commandNode;
        } catch (Exception e) { // catches all exceptions because regardless of the exception, it is because the command is invalid.
            throw new InvalidCommandException(c);
        }
    }

    /**
     * @purpose overload the makeCommand with a double argument so that when a double is passed in, it creates specifically
     *          a ConstantNode. The regular makeCommand method makes a node based on the input string and a path location,
     *          but constant nodes do not have this information, so it made more sense to overload the method.
     * @param d the double that is the constant to be made
     * @return a ConstantNode with the value d
     */
    public CommandNode makeCommand(double d) {
        return new ConstantNode(d);
    }

    /**
     * @purpose special case, Name nodes do not correspond with the input. This is similar to a ConstantNode in that
     *          the parser will pass in any string, not specifically "Name" so it needs its own constructor. This
     *          is called with the node returns true for .needsName()
     * @param s the string representation of the name
     * @return the Name CommandNode that holds s as a value.
     */
    public CommandNode makeNameNode(String s) {
        return new Name(s);
    }

    /**
     * @purpose special case for VariableNodes, when the user accesses a variable they have already created. In this case,
     *          the variable name will throw an exception from the normal makeCommand function, so it needs a special
     *          method.
     * @param s the name of the variable to be accessed
     * @param uc the UserCreated object so that the value of the variable can be accessed
     * @return the Variable node holding the passed in information.
     */
    public CommandNode makeVariableNode(String s, UserCreated uc) {
        return new Variable(s, uc);
    }
}
