package com.dhcaicedoe.securitybasicinmemoryimplapi.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lists the user roles that will be used to allow or restrict access to resources.
 *
 * @author Daniel Caicedo
 * @version 1.0, 07/04/2023
 */
public enum ApplicationUserRole {

    USER(
            Arrays.asList(
                    ApplicationUserPermission.USER_GET,
                    ApplicationUserPermission.USER_INSERT
            )
    ),
    ADMIN(
            Collections.singletonList(
                    ApplicationUserPermission.ADMIN_GET
            )
    );

    private final List<ApplicationUserPermission> permissions;

    ApplicationUserRole(List<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public List<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    /**
     * Converts roles and permissions into a single SimpleGrantedAuthority array
     *
     * @return list of authorities
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> permissions =
                getPermissions()
                        .stream()
                        .map(m -> new SimpleGrantedAuthority(m.getPermission())).collect(Collectors.toList());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
