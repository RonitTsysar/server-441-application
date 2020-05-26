package com.example.server441application.exception;

public class ContactNotFoundException extends RuntimeException{

    public ContactNotFoundException(String phoneNumber) {
        super("Could not find contact with this phone number " + phoneNumber);
    }
}