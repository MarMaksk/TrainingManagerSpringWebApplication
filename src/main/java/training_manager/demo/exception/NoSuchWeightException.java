package training_manager.demo.exception;

public class NoSuchWeightException extends RuntimeException {
    public NoSuchWeightException() {
        super("No such weight");
    }

    public NoSuchWeightException(String message) {
        super(message);
    }
}
