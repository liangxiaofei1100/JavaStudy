package com.alex.study.springsecurity.domain.security.http;

import com.alex.study.springsecurity.domain.common.http.Response;

public class JwtAuthenticationResponse extends Response {

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
