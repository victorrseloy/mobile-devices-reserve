package com.beamtrail.exception;

/**
 * Exception to be thrown when an attempt to include a duplicated user in the system happens
 */
public class DuplicatedUserException extends Exception {
    public DuplicatedUserException() {
    }

    public DuplicatedUserException(String message) {
        super(message);
    }

    public DuplicatedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedUserException(Throwable cause) {
        super(cause);
    }

    public DuplicatedUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
