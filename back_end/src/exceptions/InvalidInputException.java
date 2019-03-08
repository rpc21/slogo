package exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class InvalidInputException extends Exception {
    private static final String START_OF_ERROR_MESSAGE = "Error: Invalid ";
    private String myReason;
    protected Map<String, String> myErrorMessages;
    private static final String PROPERTIES_FILE_NAME = "parser/Exceptions";

    public InvalidInputException() {
        readPropertiesFile();
    }

    private void readPropertiesFile() {
        myErrorMessages = new HashMap<>();
        ResourceBundle errorMessages = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
        for(String key : errorMessages.keySet()) {
            myErrorMessages.put(key, errorMessages.getString(key));
        }
    }

    public String getReason() {
        return myReason;
    }

    protected void setReason(String reason) {
        myReason = START_OF_ERROR_MESSAGE + reason;
    }
}
