package com.br.api.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Profile implements GrantedAuthority {
    ADMIN, USER, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}