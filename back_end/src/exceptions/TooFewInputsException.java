/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose an exception thrown when the user inputs too few inputs for a given command
 * @Dependencies subclass of the InvalidInputException
 * @Uses: Used in the parser during command validation
 */

package exceptions;

public class TooFewInputsException extends InvalidInputException {
    private static final String FEW_KEY = "Few";

    /**
     * @purpose constructor, sets the reason to be the correct error message
     */
    public TooFewInputsException() {
        super();
        setReason(myErrorMessages.get(FEW_KEY));
    }

}
