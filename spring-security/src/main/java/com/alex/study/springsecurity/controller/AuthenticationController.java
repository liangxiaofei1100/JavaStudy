package com.alex.study.springsecurity.controller;

import com.alex.study.springsecurity.domain.security.http.*;
import com.alex.study.springsecurity.security.jwt.JwtTokenUtil;
import com.alex.study.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * 处理登录认证
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        logger.info("addRegister() request=" + request);
        UserRegisterResponse response = userService.addRegister(request);
        logger.info("addRegister() response=" + response);

        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody UserLoginResponse login(@RequestBody UserLoginRequest request) throws AuthenticationException {
        logger.info("login() request=" + request);
        UserLoginResponse response = userService.login(request);
        logger.info("login() response=" + response);

        return response;
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    UserRoleResponse getUserRole(@RequestBody UserRoleRequest request) throws AuthenticationException {
        logger.info("getUserRole() request=" + request);
        UserRoleResponse response = userService.getUserRole(request);
        logger.info("getUserRole() response=" + response);

        return response;
    }
}