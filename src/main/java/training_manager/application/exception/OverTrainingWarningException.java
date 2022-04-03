package training_manager.application.exception;

public class OverTrainingWarningException extends OverParamException {
    public OverTrainingWarningException(String message) {
        super(message);
    }
}
