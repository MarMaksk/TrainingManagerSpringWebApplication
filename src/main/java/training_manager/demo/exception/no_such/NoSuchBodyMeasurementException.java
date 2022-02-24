package training_manager.demo.exception.no_such;

public class NoSuchBodyMeasurementException extends RuntimeException {
    public NoSuchBodyMeasurementException() {
        super("No such body measurement");
    }

    public NoSuchBodyMeasurementException(String message) {
        super(message);
    }
}