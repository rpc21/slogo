package exceptions;

public class InvalidVariableException extends InvalidInputException {
    private static final String VARIABLE_KEY = "Variable";

    public InvalidVariableException(String variable) {
        super();
        setReason(myErrorMessages.get(VARIABLE_KEY) + variable);
    }
}
