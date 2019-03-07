package exceptions;

public class InvalidListException extends InvalidInputException {
    private static final String LIST_KEY = "List ";

    public InvalidListException() {
        super();
        setReason(myErrorMessages.get(LIST_KEY));
    }

}
