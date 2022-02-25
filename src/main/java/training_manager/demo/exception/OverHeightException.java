package training_manager.demo.exception;


public class OverHeightException extends RuntimeException {
    public OverHeightException() {
        super("Incorrect height");
    }

    public OverHeightException(String message) {
        super(message);
    }
}
