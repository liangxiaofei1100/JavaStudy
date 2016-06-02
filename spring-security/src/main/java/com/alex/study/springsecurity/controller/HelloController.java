package com.alex.study.springsecurity.controller;

import com.alex.study.springsecurity.domain.hello.http.HelloRequest;
import com.alex.study.springsecurity.domain.hello.http.HelloResponse;
import com.alex.study.springsecurity.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController extends BaseController{
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    HelloService helloService;

    /**
     * Say a hello to test. 测试JDBCTemplate
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public
    @ResponseBody
    HelloResponse sayHelloGet() {
        HelloResponse response = helloService.sayHelloRequest();
        logger.debug("sayHelloGet() HelloResponse: " + response);
        return response;
    }

    /**
     * Say a hello to test. 测试JDBCTemplate
     */
    @RequestMapping(value = "/hello", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    HelloResponse sayHello(@RequestBody HelloRequest request) {
        logger.debug("sayHello() HelloRequest: " + request);
        HelloResponse response = helloService.sayHelloRequest();
        logger.debug("sayHello() HelloResponse: " + response);
        return response;
    }

    /**
     * Say a hello to test.测试HibernateTemplate
     */
    @RequestMapping(value = "/hello2", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    HelloResponse sayHello2(@RequestBody HelloRequest request) {
        logger.debug("sayHello2() HelloRequest: " + request);
        HelloResponse response = helloService.sayHelloRequest2();
        logger.debug("sayHello2() HelloResponse: " + response);
        return response;
    }
}
