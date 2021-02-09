package com.crud.rest.api.exception;

import java.util.Date;

/**
 * The {@code ErrorDetails} class represents error object.
 * ErrorDetails object is constructed and sent as error response from api when an error is handled by {@link ExceptionResolver}
 * @author soundar
 */
public final class ErrorDetails {
    private final Date timestamp;
    private final String message;
    private final String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = new Date(timestamp.getTime());
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
