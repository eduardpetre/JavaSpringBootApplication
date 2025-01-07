package com.JavaSpringBoot.Application.exceptions;

public class AlreadyInError extends RuntimeException {
    public AlreadyInError(String entity1, String entity2) {
        super(entity1 + " already exists in " + entity2);
    }
}
