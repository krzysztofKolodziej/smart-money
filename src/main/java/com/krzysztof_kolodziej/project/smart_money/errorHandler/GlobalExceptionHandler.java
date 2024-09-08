package com.krzysztof_kolodziej.project.smart_money.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorRespond> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(
                new ErrorRespond(ex.getStatusCode(), ex.getReason()), ex.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRespond> handlerRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorRespond(HttpStatus.INTERNAL_SERVER_ERROR, "Git repo not found"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
