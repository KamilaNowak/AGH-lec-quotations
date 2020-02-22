package com.nowak.aghquotapi.jwtUtils;

import com.nowak.aghquotapi.jwt_gens.JWTgenerator;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JWTUtils {

    Logger logger =Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt.auth.secretKey}")
    private String secretKey;

    public String getUserFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJWTtoken(String token){
       try{
           Jwts.parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token);
           return true;
       }
       catch (Exception e){
           logger.log(Level.WARNING, " Error while validating token");
           e.printStackTrace();
       }
       return false;
    }
}
