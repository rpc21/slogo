package GUI.GUI;

import exceptions.*;

@FunctionalInterface
public interface GUIExecute {
    void executeCurrentCommand(String c, String language) throws InvalidInputException;
}
