package training_manager.demo.exception;

public abstract class OverParamException extends RuntimeException {
    public OverParamException() {
    }

    public OverParamException(String message) {
        super(message);
    }
}
