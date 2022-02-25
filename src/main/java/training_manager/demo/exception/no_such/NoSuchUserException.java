package training_manager.demo.exception.no_such;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
        super("No such user");
    }

    public NoSuchUserException(String message) {
        super(message);
    }

}
