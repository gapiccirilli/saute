package com.angelopicc.saute.exception;

public class DeleteFailedException extends RuntimeException {
    
    private static final String DELETE_FAILURE_MESSAGE = "Failed to delete";

    public DeleteFailedException() {
        super(DELETE_FAILURE_MESSAGE);
    }
}
