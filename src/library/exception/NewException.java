package library.exception;

public class NewException extends Exception{

    public NewException(String message, Throwable cause) {
            super("Message: " + message + "\nCause: ", cause);
        }
}
