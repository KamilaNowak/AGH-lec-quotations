package com.nowak.aghquotapi.jwt_filters;

import com.nowak.aghquotapi.jwtUtils.JWTUtils;
import com.nowak.aghquotapi.jwt_gens.JWTgenerator;
import com.nowak.aghquotapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    private JWTgenerator jwTgenerator;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserService userService;

    Logger logger = Logger.getLogger(getClass().getName());

    private String parseJWTtoken(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(HEADER);

        if (StringUtils.hasText(header) && header.startsWith(PREFIX)) {
            return header.replace(PREFIX, "");
        } else {
            logger.log(Level.WARNING, "Cannot parse token.");
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String rawToken = parseJWTtoken(httpServletRequest);
            if (rawToken != null && jwtUtils.validateJWTtoken(rawToken)) {
                String user = jwtUtils.getUserFromToken(rawToken);

                UserDetails userDetails = userService.loadUserByUsername(user);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
