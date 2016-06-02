package com.alex.study.springsecurity.domain.common.http;

/**
 * 服务器异常错误
 */
public class ErrorResponse extends Response {

    /**
     * url
     */
    public String url;
    /**
     * 错误信息
     */
    public String errorMessage;

    public ErrorResponse() {
        build(ResponseHeader.RESULT_CODE_SERVER_ERROR, ResponseHeader.RESULT_TEXT_SERVER_ERROR);
    }

}
