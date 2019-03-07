package exceptions;

public class InvalidCommandException extends InvalidInputException {
    private static final String COMMAND_KEY = "Command ";

    public InvalidCommandException(String command) {
        super();
        setReason(COMMAND_KEY + command);
    }
}
