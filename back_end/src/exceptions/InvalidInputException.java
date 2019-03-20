/**
 * @Author Megan Phibbons
 * @Date March 2019
 * @Purpose The superclass of all exceptions that allows for only one exception type to be thrown to the front-end
 * @Dependencies none
 * @Uses: Used throughout most of the back-end when doing validation, but mostly in Parser and Validator. Also used in
 *        front-end when the exception is finally caught and the reason is printed.
 */

package exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class InvalidInputException extends Exception {
    private static final String START_OF_ERROR_MESSAGE = "Error: ";
    private String myReason;
    protected Map<String, String> myErrorMessages;
    private static final String PROPERTIES_FILE_NAME = "parser/Exceptions";

    /**
     * @purpose constructor, initializes the values of the error messages HashMap using the properties file
     */
    public InvalidInputException() {
        readPropertiesFile();
    }

    /**
     * @purpose allow the front-end to print the reason that the exception is being thrown
     * @return the reason stored in myReason
     */
    public String getReason() {
        return myReason;
    }

    /**
     * @purpose set the reason to what it should be based on the specific sub-class. This is protected because
     *          only subclasses will ever need to call this method
     * @param reason the string corresponding with the error message (sometimes with extras based on inputs to the constructor)
     */
    protected void setReason(String reason) {
        myReason = START_OF_ERROR_MESSAGE + reason;
    }

    private void readPropertiesFile() {
        myErrorMessages = new HashMap<>();
        ResourceBundle errorMessages = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
        for(String key : errorMessages.keySet()) {
            myErrorMessages.put(key, errorMessages.getString(key));
        }
    }
}
