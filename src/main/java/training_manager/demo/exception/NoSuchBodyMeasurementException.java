package training_manager.demo.exception;

public class NoSuchBodyMeasurementException extends RuntimeException {
    public NoSuchBodyMeasurementException() {
        super("No such body measurement");
    }

    public NoSuchBodyMeasurementException(String message) {
        super(message);
    }
}
