package training_manager.demo.exception.no_such;

public class NoSuchUserStatisticException extends TrainingEntityException {
    public NoSuchUserStatisticException() {
        super("No such user statistic");
    }

    public NoSuchUserStatisticException(String message) {
        super(message);
    }
}
