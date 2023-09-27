package com.example.bankwebapp.exceptions;

public class NotFoundAccountException extends RuntimeException {

    public NotFoundAccountException(String message) {
        super(message);
    }
}