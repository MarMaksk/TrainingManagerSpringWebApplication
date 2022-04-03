package training_manager.application.exception;

public abstract class OverParamException extends RuntimeException {
    public OverParamException() {
    }

    public OverParamException(String message) {
        super(message);
    }
}
