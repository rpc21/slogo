package GUI.GUI;

import exceptions.InvalidCommandException;
import exceptions.InvalidListException;
import exceptions.InvalidVariableException;
import exceptions.NothingToRunException;

import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface GUIExecute {
    void executeCurrentCommand(String c, String language) throws InvalidCommandException, InvalidVariableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, InvalidListException, NothingToRunException;
}
