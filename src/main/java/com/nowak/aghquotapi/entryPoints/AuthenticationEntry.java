package com.nowak.aghquotapi.entryPoints;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AuthenticationEntry implements AuthenticationEntryPoint {

    Logger logger = Logger.getLogger(getClass().getName());
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, AuthenticationException exc)
            throws IOException, ServletException {
        logger.log(Level.WARNING, " Authentication failed : " + exc.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
