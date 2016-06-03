package com.alex.study.springsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHeader = "Authorization";

    protected JwtAuthenticationTokenFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpRequest, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        logger.info("attemptAuthentication " + httpRequest);
        String authToken = httpRequest.getHeader(this.tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            logger.info("attemptAuthentication before");
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                logger.info("attemptAuthentication success.");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return this.getAuthenticationManager().authenticate(authentication);
            }
            logger.info("attemptAuthentication done");
        }
        return null;
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        logger.info("doFilter");
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String authToken = httpRequest.getHeader(this.tokenHeader);
//        String username = jwtTokenUtil.getUsernameFromToken(authToken);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            logger.info("validateToken before");
//            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                logger.info("validateToken success.");
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//            logger.info("validateToken done");
//        }
//
//        chain.doFilter(request, response);
//    }


}