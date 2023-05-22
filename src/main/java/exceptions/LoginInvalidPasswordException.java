package exceptions;

public class LoginInvalidPasswordException extends LoginException {
    public LoginInvalidPasswordException(String message) {
        super(message);
    }
}
