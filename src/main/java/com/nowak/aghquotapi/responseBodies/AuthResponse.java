package com.nowak.aghquotapi.responseBodies;

import com.nowak.aghquotapi.entities.Authority;
import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.requestBodies.UserDto;

import java.util.List;

public class AuthResponse {

    private String token;
    private UserResponse userResponse;

    public AuthResponse(String token, UserResponse userResponse) {
        this.token = token;
        this.userResponse = userResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public AuthResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




}
