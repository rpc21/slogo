package GUI.Commands;

import java.util.function.Consumer;

/**
 * Classes that implement the CommandExecutable interface have the ability to run turtle commands through the parser
 * and have the result show on the front end.  This interface allows us to make sure any changes made by the user
 * using gui components other than the command line have their changes updated in the backend as well
 */
public interface CommandExecutable {

    /**
     * Gives the class the ability to run commands by passing a consumer that takes a String and passes
     * the command to the parser to be run through the backend and have its effects displayed on the front end as
     * well as stored in the backend
     * @param commandAccess a consumer that feeds the command to the parser to run the command through the backend.
     */
    void giveAbilityToRunCommands(Consumer<String> commandAccess);

    /**
     * Method that calls the accept method on the consumer that was passed in the giveAbilityToRunCommands method
     * @param command the command to be run
     */
    void runCommand(String command);
}
