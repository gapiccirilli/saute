package com.angelopicc.saute.exception.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.angelopicc.saute.exception.DeleteFailedException;
import com.angelopicc.saute.exception.DuplicateNameException;
import com.angelopicc.saute.exception.NoBooksFoundException;
import com.angelopicc.saute.exception.NoIngredientsFoundException;
import com.angelopicc.saute.exception.NoRecipesFoundException;
import com.angelopicc.saute.exception.NoShoppingListsFoundException;
import com.angelopicc.saute.exception.RecipeAlreadyExists;
import com.angelopicc.saute.exception.UserExistsException;
import com.angelopicc.saute.exception.UserNotFoundException;
import com.angelopicc.saute.payload.Error;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<Error> handleDeleteFailedException(DeleteFailedException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<Error> handleDuplicateNameException(DuplicateNameException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoIngredientsFoundException.class)
    public ResponseEntity<Error> handleNoIngredientsFoundException(NoIngredientsFoundException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoBooksFoundException.class)
    public ResponseEntity<Error> handleNoBooksFoundException(NoBooksFoundException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecipesFoundException.class)
    public ResponseEntity<Error> handleNoRecipesFoundException(NoRecipesFoundException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoShoppingListsFoundException.class)
    public ResponseEntity<Error> handleNoShoppingListsFoundException(NoShoppingListsFoundException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecipeAlreadyExists.class)
    public ResponseEntity<Error> handleRecipeAlreadyExistsException(Exception exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(Exception exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Error> handleUserExistsException(Exception exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Error> handleAuthenticationException(Exception exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGlobalException(Exception exception, WebRequest request) {
        Error error = new Error(LocalDateTime.of(LocalDate.now(), LocalTime.now()), exception.getMessage(), 
        request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
