package com.crud.rest.api.exception;

import java.util.Date;

/**
 * The {@code ErrorDetails} class represents error object.
 * ErrorDetails object is constructed and sent as error response from api when an error is handled by {@link ExceptionResolver}
 */
public final class ErrorDetails {
    private final String timestamp;
    private final String message;
    private final String details;

    public ErrorDetails(String timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
