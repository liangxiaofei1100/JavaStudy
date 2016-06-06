package com.alex.study.springsecurity.domain.security.http;

import com.alex.study.springsecurity.domain.common.http.Request;

public class JwtAuthenticationRequest extends Request {
    public String username;
    public String password;
}
