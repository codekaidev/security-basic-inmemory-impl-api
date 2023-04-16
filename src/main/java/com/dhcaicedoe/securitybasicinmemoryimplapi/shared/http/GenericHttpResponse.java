package com.dhcaicedoe.securitybasicinmemoryimplapi.shared.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Generic http response classes for requests. This class will serve for both successful and error requests. Based on
 * the status of the request the properties will be displayed
 *
 * @author Daniel Caicedo
 * @since 1.0.0, 07/04/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenericHttpResponse {

    /**
     * Time at which the response is created
     */
    private LocalDateTime timeStamp;

    /**
     * Request status code
     */
    private int statusCode;

    /**
     * Status type HttpStatus
     */
    private HttpStatus status;

    /**
     * Message to display to the user interface
     */
    private String message;

    /**
     * Message to show to developers or by console
     */
    private String developerMessage;

    /**
     * List of errors in case of returning more than one error. Recommended for user errors
     */
    private List<String> errors;

    /**
     * Trace stack
     */
    private String stackTrace;

    /**
     * Information that is returned to the client in case of having to return a requested resource and the request
     * has been successful.
     */
    private Map<?, ?> data;

    /**
     * Instantiate a new list of errors
     *
     * @return Empty list of errors. It is not shown if it is empty
     */
    public List<String> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }
}
