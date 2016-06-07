package com.alex.study.springsecurity.service;

import com.alex.study.springsecurity.domain.security.http.*;

public interface UserService {

    /**
     * 注册
     */
    UserRegisterResponse addRegister(UserRegisterRequest request);

    /**
     * 登录
     */
    UserLoginResponse login(UserLoginRequest request);

    /**
     * 用户角色
     */
    UserRoleResponse getUserRole(UserRoleRequest request);
}
