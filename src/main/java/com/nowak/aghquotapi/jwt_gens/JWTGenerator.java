package com.nowak.aghquotapi.jwt_gens;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JWTgenerator {

    Logger logger =Logger.getLogger(JWTgenerator.class.getName());

    @Value("${jwt.auth.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private String expirationTime;

    // get User profile
    public String generateJWTtoken(Authentication authentication){
        org.springframework.security.core.userdetails.User user =
                (User) authentication.getPrincipal();

        logger.log(Level.INFO, " JWT generated");

    // generate JWt based on details
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

    }


}
