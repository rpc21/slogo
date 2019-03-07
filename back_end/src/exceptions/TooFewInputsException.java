package exceptions;

public class TooFewInputsException extends InvalidInputException {
    private static final String FEW_KEY = "Few ";

    public TooFewInputsException() {
        super();
        setReason(myErrorMessages.get(FEW_KEY));
    }

}
