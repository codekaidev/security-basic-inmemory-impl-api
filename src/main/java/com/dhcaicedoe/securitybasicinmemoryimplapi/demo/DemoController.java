package com.dhcaicedoe.securitybasicinmemoryimplapi.demo;

import com.dhcaicedoe.securitybasicinmemoryimplapi.shared.http.GenericHttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * Demo Controller to validate operation by roles
 *
 * @author Daniel Caicedo
 * @version 1.0.0, 07/04/2023
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    /**
     * Validates the operation of the USER role
     *
     * @return Generic response with OK status in case the request was made by a user with the USER role
     */
    @GetMapping(
            value = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericHttpResponse> getOnlyUserRole() {
        return ResponseEntity.ok(
                GenericHttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Collections.singletonMap("data", "ok"))
                        .message("Is a user role")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /**
     * Validates the operation of the USER role
     *
     * @return Generic response with OK status in case the request was made by a user with the ADMIN role
     */
    @GetMapping(
            value = "/admin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericHttpResponse> getOnlyAdminRole() {
        return ResponseEntity.ok(
                GenericHttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Collections.singletonMap("data", "ok"))
                        .message("Is a admin role")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    /**
     *
     * @return Generic response with OK status in case the request was made by a user with the ADMIN or USER role
     */
    @GetMapping(
            value = "/all-roles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<GenericHttpResponse> getAllRoles() {
        return ResponseEntity.ok(
                GenericHttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Collections.singletonMap("data", "ok"))
                        .message("Is a any role")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


}
