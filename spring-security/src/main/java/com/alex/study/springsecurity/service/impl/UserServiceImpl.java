package com.alex.study.springsecurity.service.impl;

import com.alex.study.springsecurity.dao.security.UserDao;
import com.alex.study.springsecurity.domain.security.http.*;
import com.alex.study.springsecurity.domain.security.db.User;

import com.alex.study.springsecurity.security.jwt.JwtTokenUtil;
import com.alex.study.springsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public UserRegisterResponse addRegister(UserRegisterRequest request) {
        UserRegisterResponse response = new UserRegisterResponse();

        // 检查请求参数
        String username = request.username;
        String password = request.password;

        if (username == null || username.isEmpty()) {
            response.buildFail("用户名不能为空");
        } else if (password == null || password.length() < 6) {
            response.buildFail("密码至少为6位");
        } else {
            User user = userDao.findByUsername(request.username);
            if (user != null) {
                response.buildFail("用户名已存在");
            } else {
                user = new User();
                user.setUsername(username);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);

                userDao.save(user);

                response.token = jwtTokenUtil.generateToken(user.getUuid());
                response.buildOk();
            }
        }

        return response;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();

        // 检查请求参数
        String username = request.username;
        String password = request.password;

        if (username == null || username.isEmpty()) {
            response.buildFail("用户名不能为空");
        } else if (password == null || password.length() < 6) {
            response.buildFail("密码至少为6位");
        } else {
            User user = userDao.findByUsername(request.username);
            if (user == null) {
                response.buildFail("用户不存在");
            } else {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                boolean matches = passwordEncoder.matches(password, user.getPassword());
                if (matches) {
                    response.token = jwtTokenUtil.generateToken(user.getUuid());
                    response.buildOk();
                } else {
                    response.buildFail("密码错误");
                }
            }
        }

        return response;
    }

    @Override
    public UserRoleResponse getUserRole(UserRoleRequest request) {
        UserRoleResponse roleResponse = new UserRoleResponse();

        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("authenticated = " + authenticated);
        logger.debug("authentication = " + authentication);


        roleResponse.buildOk();
        return roleResponse;
    }
}
