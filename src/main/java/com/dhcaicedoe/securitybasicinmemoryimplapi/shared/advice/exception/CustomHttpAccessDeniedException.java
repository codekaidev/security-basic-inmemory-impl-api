package com.dhcaicedoe.securitybasicinmemoryimplapi.shared.advice.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * Custom exception for AccessDeniedException type
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
public class CustomHttpAccessDeniedException extends AccessDeniedException {

    /**
     * Request status
     */
    private int httpStatus;

    public CustomHttpAccessDeniedException(String msg) {
        super(msg);
    }

    public CustomHttpAccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomHttpAccessDeniedException(String msg, int httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
