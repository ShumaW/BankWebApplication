package com.example.bankwebapp.controller;

import com.example.bankwebapp.dto.ErrorData;
import com.example.bankwebapp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorData> handleNotEnoughMoneyException(NotEnoughMoneyException exception){
        ErrorData errorData = new ErrorData(HttpStatus.EXPECTATION_FAILED, exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(NotFoundAccountException.class)
    public ResponseEntity<ErrorData> handleNotFoundAccountException(NotFoundAccountException exception){
        ErrorData errorData = new ErrorData(HttpStatus.NOT_FOUND, exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundClientException.class)
    public ResponseEntity<ErrorData> handleNotFoundClientException(NotFoundClientException exception){
        ErrorData errorData = new ErrorData(HttpStatus.NOT_FOUND, exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundManagerException.class)
    public ResponseEntity<ErrorData> handleNotFoundManagerException(NotFoundManagerException exception){
        ErrorData errorData = new ErrorData(HttpStatus.NOT_FOUND, exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundProductException.class)
    public ResponseEntity<ErrorData> handleNotFoundProductException(NotFoundProductException exception){
        ErrorData errorData = new ErrorData(HttpStatus.NOT_FOUND, exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }
}
