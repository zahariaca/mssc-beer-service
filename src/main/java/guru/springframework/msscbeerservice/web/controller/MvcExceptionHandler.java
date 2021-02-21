package guru.springframework.msscbeerservice.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
        List<String> errorsList = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(errors -> errorsList.add(errors.toString()));

        return new ResponseEntity<>(errorsList, BAD_REQUEST);
    }
}
