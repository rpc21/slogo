package exceptions;

public class NothingToRunException extends InvalidInputException {
    private static final String NOTHING_KEY = "Nothing ";

    public NothingToRunException() {
        super();
        setReason(myErrorMessages.get(NOTHING_KEY));
    }

}
