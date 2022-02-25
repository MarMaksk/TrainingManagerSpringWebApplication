package training_manager.demo.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import training_manager.demo.exception.no_such.*;

@ControllerAdvice
public class NoSuchAdvice {

    @ExceptionHandler({
            NoSuchMuscleException.class,
            NoSuchBodyMeasurementException.class,
            NoSuchRoleException.class,
            NoSuchTrainingDayException.class,
            NoSuchUserException.class,
            NoSuchUserStatisticException.class,
            NoSuchWeightException.class
    })
    public ResponseEntity<Response> handlerException(Exception e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
