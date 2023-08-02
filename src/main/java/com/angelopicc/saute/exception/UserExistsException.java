package com.angelopicc.saute.exception;

public class UserExistsException extends RuntimeException {
    
    public UserExistsException(String message) {
        super(message);
    }
}
