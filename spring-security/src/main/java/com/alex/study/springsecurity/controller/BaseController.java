package com.alex.study.springsecurity.controller;


import com.alex.study.springsecurity.domain.common.http.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共类,处理异常错误
 */
public class BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public @ResponseBody
    ErrorResponse handleException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.url = request.getRequestURL().toString();
        errorResponse.errorMessage = getStackTrace(ex);

        logger.error("handled a exception: ", ex);
        return errorResponse;
    }

    private String getStackTrace(Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(throwable.toString());
        stringBuilder.append(" ");

        // 打印3行错误栈
        int line = 0;
        StackTraceElement[] elements = throwable.getStackTrace();
        for (StackTraceElement element : elements) {
            stringBuilder.append(" ");
            stringBuilder.append(element);
            line++;
            if (line == 3) {
                break;
            }
        }

        return stringBuilder.toString();
    }
}
