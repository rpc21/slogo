/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose an exception thrown when the user inputs a variable that does not exist or of the incorrect form
 * @Dependencies subclass of the InvalidInputException
 * @Uses: Used in the parser during variable validation
 */

package exceptions;

import exceptions.external.InvalidInputException;

public class InvalidVariableException extends InvalidInputException {
    private static final String VARIABLE_KEY = "Variable";

    /**
     * @purpose constructor that sets the reason to be the variable error message
     * @param variable the variable that caused the exception to be thrown
     */
    public InvalidVariableException(String variable) {
        super();
        setReason(myErrorMessages.get(VARIABLE_KEY) + variable);
    }
}
