package org.example.exampleapp.errorhandling;

import org.example.exampleapp.model.response.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandling extends RuntimeException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> handleRuntimeException(RuntimeException ex) {
        ErrorModel error = new ErrorModel();
        error.setErrorMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setPath(ex.getStackTrace()[0].getFileName());
        return new ResponseEntity<ErrorModel>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
