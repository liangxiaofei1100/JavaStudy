package com.alex.study.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUserAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(AppUserAuthenticationProvider.class);


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("AppUserAuthenticationProvider authenticate");
        AppUserAuthenticationToken appUserAuthenticationToken = (AppUserAuthenticationToken) authentication;

        if (appUserAuthenticationToken.userToken != null && !appUserAuthenticationToken.userToken.isEmpty()) {
            appUserAuthenticationToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(appUserAuthenticationToken);

            return appUserAuthenticationToken;
        }

        throw new AuthenticationServiceException("无法找到该用户");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
