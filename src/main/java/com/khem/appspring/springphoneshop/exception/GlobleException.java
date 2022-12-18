package com.khem.appspring.springphoneshop.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 

@ControllerAdvice
public class GlobleException {

        // @ExceptionHandler(HttpClientErrorException.class)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleHttpClientError(ApiException e) {
        //ServiceException exception = new ServiceException(e.getStatusCode(), e.getMessage());

        ErrorResponse errorResponse=new ErrorResponse(e.getStatus().getReasonPhrase(), e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(errorResponse);

    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        //ServiceException exception = new ServiceException(e.getStatusCode(), e.getMessage());

        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase().toString(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
}
