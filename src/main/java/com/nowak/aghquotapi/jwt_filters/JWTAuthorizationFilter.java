package com.nowak.aghquotapi.jwt_filters;

import com.nowak.aghquotapi.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Value("${auth.secretKey}")
    private String SECRET_KEY;



    private boolean checkJWTtoken(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse){
        boolean isHeadersValid = false;
        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if ((authorizationHeader == null || !authorizationHeader.startsWith(PREFIX))) {
            isHeadersValid = false;
        } else {
            isHeadersValid = true;
        }
    return  isHeadersValid;
    }
    private Claims validateJWTtoken(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse){
        String token =httpServletRequest
                .getHeader(AUTHORIZATION_HEADER)
                .replace(PREFIX,""); //extract vanilla token
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
    private  void checkAuthentication(Claims claims, HttpServletRequest httpServletRequest){
        List<String> userAuthorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
            new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
//                userService.findByToken(httpServletRequest
//                        .getHeader(AUTHORIZATION_HEADER)
//                        .replace(PREFIX,"")).getPassword(),
                        userAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    if(checkJWTtoken(httpServletRequest,httpServletResponse)){
        Claims claims= validateJWTtoken(httpServletRequest,httpServletResponse);
        if(claims.get("authorities")!= null){
            checkAuthentication(claims,httpServletRequest);
        }
        else {
            SecurityContextHolder.clearContext();
        }
    }
    filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
