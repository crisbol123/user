package com.pragma.user.domain.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException() {
        super("Invalid phone number");
    }
}
