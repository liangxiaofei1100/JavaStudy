package com.alex.study.springsecurity.security;

import com.alex.study.springsecurity.dao.security.UserDao;
import com.alex.study.springsecurity.domain.security.db.Authority;
import com.alex.study.springsecurity.domain.security.db.User;
import com.alex.study.springsecurity.security.app.JwtUserAuthenticationToken;
import com.alex.study.springsecurity.security.jwt.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDao userDao;

    protected JwtAuthenticationTokenFilter() {
        super("/**");

        setContinueChainBeforeSuccessfulAuthentication(true);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpRequest, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        logger.info("attemptAuthentication()");
        String authToken = httpRequest.getHeader(JwtTokenUtil.TOKEN_HEADER);
        logger.info("attemptAuthentication() authToken=" + authToken);
        String userUuid = jwtTokenUtil.getUserUuidFromToken(authToken);

        // 获取用户信息和用户权限
        User user = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userUuid != null) {
            user = userDao.findByUuid(userUuid);
            if (user != null) {
                authorities = mapToGrantedAuthorities(user.getAuthorities());
            }
        }

        JwtUserAuthenticationToken appUserAuthenticationToken = new JwtUserAuthenticationToken(authorities);
        appUserAuthenticationToken.userUuid = userUuid;
        appUserAuthenticationToken.user = user;

        return getAuthenticationManager().authenticate(appUserAuthenticationToken);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}