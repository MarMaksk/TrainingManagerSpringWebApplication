package training_manager.demo.exception;

public class NoSuchUserStatisticException extends RuntimeException {
    public NoSuchUserStatisticException() {
        super("No such user statistic");
    }

    public NoSuchUserStatisticException(String message) {
        super(message);
    }
}
