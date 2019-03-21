/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose an exception thrown when the given command does not exist or is in the wrong language
 * @Dependencies subclass of the InvalidInputException
 * @Uses: Used in the parser during command validation
 */

package exceptions;

import exceptions.external.InvalidInputException;

public class InvalidCommandException extends InvalidInputException {
    private static final String COMMAND_KEY = "Command";

    /**
     * @purpose constructor, sets the reason for the exception being thrown
     * @param command the name of the command that caused the exception to be thrown
     */
    public InvalidCommandException(String command) {
        super();
        setReason(myErrorMessages.get(COMMAND_KEY) + command);
    }
}
