package com.beamtrail.exception;

/**
 * Exception thrown when inconsistent operations are executed on a phone booking, like trying to book an already
 * booked phone or return an already returned phone
 */
public class InconsistentPhoneBookingException extends Exception {
    public InconsistentPhoneBookingException() {
    }

    public InconsistentPhoneBookingException(String message) {
        super(message);
    }

    public InconsistentPhoneBookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistentPhoneBookingException(Throwable cause) {
        super(cause);
    }

    public InconsistentPhoneBookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
