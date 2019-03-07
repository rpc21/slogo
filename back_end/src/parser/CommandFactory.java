package parser;

import apis.AddVariable;
import apis.GetVariableValue;
import nodes.CommandNode;
import nodes.ConstantNode;
import nodes.Name;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory {

    public CommandNode makeCommand(double d) {
        return new ConstantNode(d);
    }

    public CommandNode makeCommand(String c, UserCreated userCreated) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class commandClass = Class.forName("nodes." + c);
        Constructor constructor = commandClass.getConstructor(String.class);
        CommandNode commandNode = (CommandNode) constructor.newInstance(c);
        if(commandNode.needsUserCreated()) {
            constructor = commandClass.getConstructor(String.class, AddVariable.class);
            commandNode = (CommandNode) constructor.newInstance(c, userCreated);
        }
        return commandNode;
    }

    public CommandNode makeNameNode(String s) {
        return new Name(s);
    }
}
