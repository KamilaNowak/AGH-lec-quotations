package com.nowak.aghquotapi.responseBodies;

import com.nowak.aghquotapi.entities.Authority;

import java.util.Collection;

public class UserResponse {
    private String name;
    private String email;
    private Collection<Authority> authorities;

    public UserResponse(String name, String email, Collection<Authority> authorities) {
        this.name = name;
        this.email = email;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }
}
