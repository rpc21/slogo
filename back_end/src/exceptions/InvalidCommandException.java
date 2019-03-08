package exceptions;

public class InvalidCommandException extends InvalidInputException {
    private static final String COMMAND_KEY = "Command";

    public InvalidCommandException(String command) {
        super();
        setReason(myErrorMessages.get(COMMAND_KEY) + command);
    }
}
