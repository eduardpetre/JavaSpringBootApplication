package com.JavaSpringBoot.Application.handlers;

import com.JavaSpringBoot.Application.exceptions.AlreadyInError;
import com.JavaSpringBoot.Application.exceptions.EntityDuplicateError;
import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            {AlreadyInError.class,
            EntityDuplicateError.class,
            EntityNotFoundError.class,
            UnmatchedIdError.class})
    public ResponseEntity handle(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
