package GUI.GUI;

import exceptions.*;

import java.lang.reflect.InvocationTargetException;

@FunctionalInterface
public interface GUIExecute {
    void executeCurrentCommand(String c, String language) throws InvalidInputException;
}
