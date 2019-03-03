package exceptions;

public class InvalidCommandException extends InvalidInputException {

    public InvalidCommandException(String command) {
        super(command);
    }
}
