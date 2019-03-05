package GUI;

import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;

import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface GUIExecute {
    void executeCurrentCommand(String c, String language) throws InvalidCommandException, InvalidVariableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
