package exceptions;

public class MinhaException extends RuntimeException {
    public MinhaException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinhaException(String message) {
        super(message);
    }
}
