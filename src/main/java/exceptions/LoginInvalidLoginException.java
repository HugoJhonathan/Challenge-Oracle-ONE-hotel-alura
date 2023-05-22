package exceptions;

public class LoginInvalidLoginException extends LoginException {
    public LoginInvalidLoginException(String message) {
        super(message);
    }
}
