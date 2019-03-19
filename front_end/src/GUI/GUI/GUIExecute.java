package GUI.GUI;

import exceptions.*;

/**
 * Interface that indicates a class can execute commands within itself
 */
@FunctionalInterface
public interface GUIExecute {

    /**
     * Run a command through the back end
     * @param c Command to be executed
     * @param language Current language the user is typing in
     * @throws InvalidInputException Exception thrown when user enters incorrect command
     */
    void executeCurrentCommand(String c, String language) throws InvalidInputException;
}
