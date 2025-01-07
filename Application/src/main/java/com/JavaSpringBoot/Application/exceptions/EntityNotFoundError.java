package com.JavaSpringBoot.Application.exceptions;

public class EntityNotFoundError extends RuntimeException {
    public EntityNotFoundError(String entity) {
        super(entity + " not found!");
    }
}
