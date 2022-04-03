package training_manager.application.exception.no_such;

public class NoSuchWeightException extends TrainingEntityException {
    public NoSuchWeightException() {
        super("No such weight");
    }

    public NoSuchWeightException(String message) {
        super(message);
    }
}
