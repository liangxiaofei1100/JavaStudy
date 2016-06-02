package com.alex.study.springsecurity.domain.hello.http;


import com.alex.study.springsecurity.domain.common.http.Response;

public class HelloResponse extends Response {
    public String hello = "Hello, It's me.";

    @Override
    public String toString() {
        return "HelloResponse{" +
                "hello='" + hello + '\'' +
                '}';
    }
}
