package exceptions;

import views.WindowAlert;

import java.util.NoSuchElementException;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof MinhaException || e instanceof DatabaseException) {
            new WindowAlert(e.getMessage(), WindowAlert.Tipos.ERROR);
        }
        if (e instanceof LoginException || e instanceof NoSuchElementException) {
            new WindowAlert(e.getMessage(), WindowAlert.Tipos.ALERT);
        }
        e.printStackTrace();
    }

}