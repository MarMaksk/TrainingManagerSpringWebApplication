package training_manager.demo.exception;

public class OverloadException extends OverParamException {
    public OverloadException() {
        super("Overload weight. Take care of yourself");
    }

    public OverloadException(String message) {
        super(message);
    }
}
