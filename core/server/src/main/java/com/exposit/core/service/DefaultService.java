package com.exposit.core.service;

public interface DefaultService {
    default void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
