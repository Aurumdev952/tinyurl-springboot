package com.tinyurl.tinyurl.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(CustomRequestException.class)
    public ResponseEntity<Object> handleCustomRequestException(CustomRequestException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
//        ex.printStackTrace();
        log.error(ex.getMessage(), ex.getCause() != null ? ex.getCause().getMessage() : "No cause");
        return new ResponseEntity<>(body, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
//        ex.printStackTrace();
        log.error(ex.getMessage(), ex.getCause() != null ? ex.getCause().getMessage() : "No cause");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}