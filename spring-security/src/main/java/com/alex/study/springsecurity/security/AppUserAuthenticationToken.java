package com.alex.study.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * app用户认证token
 */
public class AppUserAuthenticationToken extends AbstractAuthenticationToken {
    Logger logger = LoggerFactory.getLogger(AppUserAuthenticationToken.class);

    public String userToken;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public AppUserAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        logger.debug("getCredentials");
        return null;
    }

    @Override
    public Object getPrincipal() {
        logger.debug("getPrincipal");
        return null;
    }
}
