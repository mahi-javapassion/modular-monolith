package com.mahi.javapassion.coaching.common.exception;

/**
 * Represents Resource Not Found Exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
