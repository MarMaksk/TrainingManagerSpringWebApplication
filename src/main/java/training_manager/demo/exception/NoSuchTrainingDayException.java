package training_manager.demo.exception;

public class NoSuchTrainingDayException extends RuntimeException {
    public NoSuchTrainingDayException() {
        super("No such training day");
    }

    public NoSuchTrainingDayException(String message) {
        super(message);
    }
}
