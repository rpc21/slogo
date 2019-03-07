package parser;

import apis.AddVariable;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
import nodes.CommandNode;
import nodes.ConstantNode;
import nodes.Name;

import java.lang.reflect.Constructor;

public class CommandFactory {

    public CommandNode makeCommand(double d) {
        return new ConstantNode(d);
    }

    public CommandNode makeCommand(String c, UserCreated userCreated) throws InvalidInputException {
        CommandNode commandNode = null;
        try {
            Class commandClass = Class.forName("nodes." + c);
            Constructor constructor = commandClass.getConstructor(String.class);
            commandNode = (CommandNode) constructor.newInstance(c);
            if (commandNode.needsUserCreated()) {
                constructor = commandClass.getConstructor(String.class, AddVariable.class);
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
}
