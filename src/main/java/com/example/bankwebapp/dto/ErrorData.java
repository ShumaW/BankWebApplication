package com.example.bankwebapp.dto;

import lombok.Value;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Value
public class ErrorData {

    HttpStatus status;
    String exceptionMessage;
    String stackTrace;
    Timestamp timestamp;

    public ErrorData(HttpStatus status, String exceptionMessage, String stackTrace) {
        this.status = status;
        this.exceptionMessage = exceptionMessage;
        this.stackTrace = stackTrace;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
