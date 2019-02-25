package com.beamtrail.exception;

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
