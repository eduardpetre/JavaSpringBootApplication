package com.JavaSpringBoot.Application.exceptions;

public class UnmatchedIdError extends RuntimeException {
    public UnmatchedIdError(String entity1) {
        super(entity1 + " mismatched id");
    }
    public UnmatchedIdError(String entity1, String entity2) {
        super("Unmatched id " + entity1 + " and " + entity2);
    }
}
