package com.khem.appspring.springphoneshop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
 

@ControllerAdvice
public class GlobleException {

        // @ExceptionHandler(HttpClientErrorException.class)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleHttpClientError(ApiException e) {
        //ServiceException exception = new ServiceException(e.getStatusCode(), e.getMessage());

        //TODO customize respond DTO message

        ErrorResponse errorResponse=new ErrorResponse(e.getStatus().getReasonPhrase(), e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(errorResponse);

    }
}
