package training_manager.application.exception.no_such;

public class NoSuchUserException extends TrainingEntityException {

    public NoSuchUserException() {
        super("No such user");
    }

    public NoSuchUserException(String message) {
        super(message);
    }

}
