package org.example.exampleapp.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandling extends RuntimeException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Return a custom response entity with the error message and a status code
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
