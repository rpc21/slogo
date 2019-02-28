package exceptions;

public class InvalidCommandException extends Exception {
    private String myReason;

    public InvalidCommandException(String reason) {
        myReason = reason;
    }

    public String getReason() {
        return myReason;
    }
}
