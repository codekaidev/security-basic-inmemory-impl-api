package com.dhcaicedoe.securitybasicinmemoryimplapi.shared.advice.exception;

import com.dhcaicedoe.securitybasicinmemoryimplapi.shared.http.GenericHttpResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Consejo para el manejo de excepciones
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@RestControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle exceptions for methods not found
     *
     * @param ex Exception to the error to be handled
     * @param headers
     * @param status
     * @param request
     * @return Response to the request that generated the exception
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status,
                                                                         WebRequest request) {
        GenericHttpResponse genericHttpResponse =
                GenericHttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(null)
                        .message(ex.getMessage())
                        .status(status)
                        .statusCode(status.value())
                        .developerMessage(ex.getMessage())
                        .build();

        Objects.requireNonNull(ex.getSupportedHttpMethods())
                .stream()
                .map(Enum::name)
                .forEach(genericHttpResponse.getErrors()::add);
        return new ResponseEntity<Object>(genericHttpResponse, genericHttpResponse.getStatus());
    }

    /**
     * Handle exceptions for denied access
     *
     * @param ex Exception to the error to be handled
     * @return Response to the request that generated the exception
     */
    @ExceptionHandler({CustomHttpAccessDeniedException.class})
    public ResponseEntity<GenericHttpResponse> handleHttpAccessDeniedException(CustomHttpAccessDeniedException ex) {
        GenericHttpResponse genericHttpResponse =
                GenericHttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(null)
                        .message(ex.getMessage())
                        .status(HttpStatus.FORBIDDEN)
                        .statusCode(ex.getHttpStatus())
                        .developerMessage(ex.getMessage())
                        .build();
        return new ResponseEntity<>(genericHttpResponse, genericHttpResponse.getStatus());
    }

    /**
     * Handle all exceptions
     *
     * @param ex Exception to the error to be handled
     * @return Response to the request that generated the exception
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<GenericHttpResponse> handleAllException(Exception ex) {

        // Gets the trace stack
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        GenericHttpResponse genericHttpResponse =
                GenericHttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(null)
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getMessage())
                        .stackTrace(stackTrace)
                        .build();
        return new ResponseEntity<>(genericHttpResponse, genericHttpResponse.getStatus());
    }


}
