package exceptions;

public abstract class InvalidInputException extends Exception {
    private String myReason;

    public InvalidInputException() {
        this("Unknown");
    }

    public InvalidInputException(String reason) {
        myReason = reason;
    }

    public String getReason() {
        return myReason;
    }
}
