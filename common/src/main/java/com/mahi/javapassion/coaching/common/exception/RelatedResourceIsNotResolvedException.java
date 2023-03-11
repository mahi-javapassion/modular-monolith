package com.mahi.javapassion.coaching.common.exception;

/**
 * Represents exception for the cases when related resource is not resolved
 */
public class RelatedResourceIsNotResolvedException extends RuntimeException {

    public RelatedResourceIsNotResolvedException(String message) {
        super(message);
    }
}
