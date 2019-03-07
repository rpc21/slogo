package exceptions;

public class TooManyInputsException extends InvalidInputException {
    private static final String MANY_KEY = "Many";

    public TooManyInputsException() {
        super();
        setReason(myErrorMessages.get(MANY_KEY));
    }

}
