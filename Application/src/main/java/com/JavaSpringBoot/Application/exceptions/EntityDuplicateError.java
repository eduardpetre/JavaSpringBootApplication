package com.JavaSpringBoot.Application.exceptions;

public class EntityDuplicateError extends RuntimeException {
  public EntityDuplicateError(String entity) {
    super(entity + " already exists!");
  }
}
