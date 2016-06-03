package com.alex.study.springsecurity.security;

import com.alex.study.springsecurity.domain.common.http.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Jwt权限认证失败
@Component
public class JwtAuthenticationFailEntryPoint implements AuthenticationEntryPoint {
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFailEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        Response unauthorizedResponse = new Response();
        unauthorizedResponse.buildFail("权限不足");

        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), unauthorizedResponse);

        logger.debug("commence Unautorized");
    }
}
