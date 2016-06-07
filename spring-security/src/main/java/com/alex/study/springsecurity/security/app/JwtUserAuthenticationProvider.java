package com.alex.study.springsecurity.security.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 自定义的Provider,验证JWT
 */
public class JwtUserAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(JwtUserAuthenticationProvider.class);


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("JwtUserAuthenticationProvider authenticate");
        // 这里所有用户都认证通过, 需要权限的地方再做检查
        JwtUserAuthenticationToken jwtUserAuthenticationToken = (JwtUserAuthenticationToken) authentication;
        jwtUserAuthenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(jwtUserAuthenticationToken);

        return jwtUserAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
