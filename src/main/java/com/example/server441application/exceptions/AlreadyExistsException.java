package com.example.server441application.exceptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String phoneNumber) {
        super("This phone number is already exists " + phoneNumber);
    }
}
