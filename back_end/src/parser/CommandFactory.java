package parser;

import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
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

    public CommandNode makeCommand(double d) {
        return new ConstantNode(d);
    }

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
        } catch (Exception e) {
            throw new InvalidCommandException(c);
        }
    }

    public CommandNode makeNameNode(String s) {
        return new Name(s);
    }

    public CommandNode makeVariableNode(String s, UserCreated uc) throws InvalidInputException {
        return new Variable(s, uc);
    }
}
