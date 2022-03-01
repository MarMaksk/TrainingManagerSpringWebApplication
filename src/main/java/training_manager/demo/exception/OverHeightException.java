package training_manager.demo.exception;


public class OverHeightException extends OverParamException {
    public OverHeightException() {
        super("Incorrect height");
    }

    public OverHeightException(String message) {
        super(message);
    }
}
