package com.example.bankwebapp.exceptions;

public class NotFoundProductException extends RuntimeException{
    public NotFoundProductException(String message) {
        super(message);
    }
}