package com.br.api.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Profile implements GrantedAuthority {
    ADMIN, SORTER, FINISHER;

    @Override
    public String getAuthority() {
        return name();
    }
}