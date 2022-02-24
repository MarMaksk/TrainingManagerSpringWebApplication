package training_manager.demo.exception;

public class OvertrainingWarningException extends RuntimeException {
    public OvertrainingWarningException(String message) {
        super(message);
    }
}
