package com.nowak.aghquotapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nowak.aghquotapi.jwt_gens.JWTgenerator;
import com.nowak.aghquotapi.requestBodies.LoginDto;
import com.nowak.aghquotapi.requestBodies.UserDto;
import com.nowak.aghquotapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTgenerator jwTgenerator;
    Logger logger = Logger.getLogger(getClass().getName());

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        logger.log(Level.INFO, " Registering user with name : " + userDto.getName());
        if (userService.findByUsername(userDto.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        userService.registerUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public String getUserProfile(@RequestBody LoginDto loginDto) {
        logger.log(Level.INFO,"Authenticating user : "+loginDto.getName());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword()));
        logger.log(Level.INFO, "User authentication ended successfully");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwTgenerator.generateJWTtoken(authentication);
        return token;
    }
}
