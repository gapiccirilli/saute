package com.angelopicc.saute.exception;

public class RecipeAlreadyExists extends RuntimeException {
    
    public RecipeAlreadyExists(String message) {
        super(message);
    }
}
