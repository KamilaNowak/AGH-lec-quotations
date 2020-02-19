package com.nowak.aghquotapi.controllers;

import com.nowak.aghquotapi.entities.User;
import com.nowak.aghquotapi.requestBodies.UserData;
import com.nowak.aghquotapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody UserData userData) {
       Optional<User> existUser =
                Optional.ofNullable(userService.findByUsername(userData.getName()));
        if (existUser.isPresent()==true) {
            return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
        } else {
            userService.registerUser(userData);
       }
        return new ResponseEntity<>("Registered", HttpStatus.CREATED);
    }
}
