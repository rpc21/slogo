/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose an exception thrown when the user starts a list but never ends it
 * @Dependencies subclass of the InvalidInputException
 * @Uses: Used in the parser during list parsing
 */

package exceptions;

public class InvalidListException extends InvalidInputException {
    private static final String LIST_KEY = "List";

    /**
     * @purpose constructor, sets the error message to the corresponding string in the properties file
     */
    public InvalidListException() {
        super();
        setReason(myErrorMessages.get(LIST_KEY));
    }

}
