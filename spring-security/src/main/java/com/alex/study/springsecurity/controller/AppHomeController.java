package com.alex.study.springsecurity.controller;

import com.alex.study.springsecurity.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppHomeController extends BaseController{
    Logger logger = LoggerFactory.getLogger(AppHomeController.class);

    @Autowired
    HelloService helloService;

    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    @RequestMapping(value = "/", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String home() {
        String helloMessage = helloService.sayHelloHome();

        String message = "欢迎来到App主页，当前时间：" + format.format(new Date());
        message += "\n";
        message += helloMessage;
        logger.info("App home. message: " + message);

        return message;
    }
}
