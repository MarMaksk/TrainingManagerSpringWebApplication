package training_manager.demo.exception;

public class NoSuchRoleException extends RuntimeException {
    public NoSuchRoleException() {
        super("No such role");
    }

    public NoSuchRoleException(String message) {
        super(message);
    }
}
