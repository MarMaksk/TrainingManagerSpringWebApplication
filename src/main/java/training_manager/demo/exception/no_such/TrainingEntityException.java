package training_manager.demo.exception.no_such;

public abstract class TrainingEntityException extends RuntimeException {
    public TrainingEntityException() {
    }

    public TrainingEntityException(String message) {
        super(message);
    }
}
