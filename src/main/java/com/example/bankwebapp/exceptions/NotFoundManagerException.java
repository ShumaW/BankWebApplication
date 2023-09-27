package com.example.bankwebapp.exceptions;

public class NotFoundManagerException extends RuntimeException{

    public NotFoundManagerException(String message) {
        super(message);
    }
}