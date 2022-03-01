package training_manager.demo.exception.no_such;

public class NoSuchRoleException extends TrainingEntityException {
    public NoSuchRoleException() {
        super("No such role");
    }

    public NoSuchRoleException(String message) {
        super(message);
    }
}
