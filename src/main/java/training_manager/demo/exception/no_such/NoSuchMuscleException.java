package training_manager.demo.exception.no_such;

public class NoSuchMuscleException extends TrainingEntityException {
    public NoSuchMuscleException() {
        super("No such muscle exception");
    }

    public NoSuchMuscleException(String message) {
        super(message);
    }
}
