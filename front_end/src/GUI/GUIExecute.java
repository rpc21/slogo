package GUI;

import exceptions.InvalidCommandException;
import exceptions.InvalidVariableException;

@FunctionalInterface
public interface GUIExecute {
    void executeCurrentCommand(String c, String language) throws InvalidCommandException, InvalidVariableException;
}
