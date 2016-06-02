package com.alex.study.springsecurity.domain.common.http;


public class Request {
    public RequestHeader header;

    @Override
    public String toString() {
        return "Request{" +
                "header=" + header +
                '}';
    }
}
