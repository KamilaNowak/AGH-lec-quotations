package com.nowak.aghquotapi.controllers;


import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.requestBodies.UserDto;
import com.nowak.aghquotapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        //TODO
        return null;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile(Authentication authentication){
        //TODO
        return null;
    }
}
