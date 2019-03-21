/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose an exception thrown when the user inputs only comments or an empty string
 * @Dependencies subclass of the InvalidInputException
 * @Uses: Used in the parser when the command is initially run
 */

package exceptions;

import exceptions.external.InvalidInputException;

public class NothingToRunException extends InvalidInputException {
    private static final String NOTHING_KEY = "Nothing";

    /**
     * @purpose constructor, sets the error message to be what it should be based on the properties file
     */
    public NothingToRunException() {
        super();
        setReason(myErrorMessages.get(NOTHING_KEY));
    }

}
