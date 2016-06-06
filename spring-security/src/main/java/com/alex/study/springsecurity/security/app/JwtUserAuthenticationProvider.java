package com.alex.study.springsecurity.security.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUserAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(JwtUserAuthenticationProvider.class);


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("JwtUserAuthenticationProvider authenticate");
        JwtUserAuthenticationToken jwtUserAuthenticationToken = (JwtUserAuthenticationToken) authentication;

        if (jwtUserAuthenticationToken.userToken != null && !jwtUserAuthenticationToken.userToken.isEmpty()) {
            jwtUserAuthenticationToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtUserAuthenticationToken);

            return jwtUserAuthenticationToken;
        }

        throw new AuthenticationServiceException("无法找到该用户");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
