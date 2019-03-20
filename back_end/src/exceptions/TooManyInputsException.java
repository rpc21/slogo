/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose an exception thrown when the user inputs too many inputs for a given command
 * @Dependencies subclass of the InvalidInputException
 * @Uses: Used in the parser during command validation
 */

package exceptions;

public class TooManyInputsException extends InvalidInputException {
    private static final String MANY_KEY = "Many";

    /**
     * @purpose constructor, sets the error to be the corresponding error message
     */
    public TooManyInputsException() {
        super();
        setReason(myErrorMessages.get(MANY_KEY));
    }

}
