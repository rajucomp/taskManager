package exceptions;

public class NewProcessNotAllowedException extends RuntimeException {

    public NewProcessNotAllowedException(String message) {
        super(message);
    }
}
