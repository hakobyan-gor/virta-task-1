package com.task.controller;

import com.task.util.error.ValidationError;
import com.task.util.error.exceptions.BadRequestException;
import com.task.util.error.exceptions.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.task.util.error.Error;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandlingAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> handleNoSuchElementException(NoSuchElementException nse, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Error.notFound(nse.getMessage()).requestParams(request));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException bre, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.badRequest(bre.getMessage()).requestParams(request));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Error> handleConflictException(ConflictException ce, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Error.conflict(ce.getMessage()).requestParams(request));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ValidationError validationError = new ValidationError(((FieldError) error).getField(), error.getDefaultMessage());
            errors.add(validationError);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.badRequest(ex.getClass().getPackageName(), errors).requestParams(request));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleNotReadableExceptions(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Error.unprocessableEntity(ex.getClass().getPackageName(), "cannot.process.entity").requestParams(request));
    }

}