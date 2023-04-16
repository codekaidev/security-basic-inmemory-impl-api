package com.dhcaicedoe.securitybasicinmemoryimplapi.security;

/**
 * Lists the permissions a role has
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
public enum ApplicationUserPermission {
    USER_GET("user:get"),
    USER_INSERT("user:insert"),
    ADMIN_GET("admin:get"),
    ADMIN_INSERT("admin:insert");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
