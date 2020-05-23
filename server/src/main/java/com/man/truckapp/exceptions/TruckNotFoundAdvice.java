package com.man.truckapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * TruckNotFoundAdvice
 */
@RestControllerAdvice
public class TruckNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TruckNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    String truckNotFoundHandler(TruckNotFoundException exception) {
        return exception.getMessage();
    }

}