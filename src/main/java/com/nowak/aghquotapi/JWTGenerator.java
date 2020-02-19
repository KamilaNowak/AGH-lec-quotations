package com.nowak.aghquotapi;

import com.nowak.aghquotapi.requestBodies.UserData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JWTGenerator {

    @Value("${auth.accessKey}")
    private String secretKey;

    public String generateJWTtoken(UserData userData){

        List<GrantedAuthority> grantedAuthoritydAList = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String JWTtoken= Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userData.getName())
                .claim("authorities",
                        grantedAuthoritydAList.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ (600000 *31))) //token for a month
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes())
                .compact();

        return  JWTtoken;

    }
}
