package com.dhcaicedoe.securitybasicinmemoryimplapi.shared.advice.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Handle an AccessDeniedException.
 * @author Daniel Caicedo
 * @since 1.0.0
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     *   Handle an AccessDeniedException.
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String errorMessage = String.format("User %s does not have accesss", authentication.getName());
            throw new CustomHttpAccessDeniedException(errorMessage, response.getStatus());
    }
}
