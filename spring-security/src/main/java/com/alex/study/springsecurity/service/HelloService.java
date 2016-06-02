package com.alex.study.springsecurity.service;


import com.alex.study.springsecurity.dao.hello.HelloDao;
import com.alex.study.springsecurity.domain.hello.db.GreetingEntity;
import com.alex.study.springsecurity.domain.hello.http.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    HelloDao helloDao;

    /**
     * 获取消息,使用JdbcTemplate查询数据
     */
    public HelloResponse sayHelloRequest() {
        HelloResponse response = new HelloResponse();

        GreetingEntity greeting = helloDao.sayHello();
        if (greeting != null) {
            response.hello = greeting.message;
        }

        response.buildOk();
        return response;
    }

    /**
     * 获取消息,使用HibernateTemplate查询数据
     */
    public HelloResponse sayHelloRequest2() {
        HelloResponse response = new HelloResponse();

        GreetingEntity greeting = helloDao.sayHello2();
        if (greeting != null) {
            response.hello = greeting.message;
        }

        response.buildOk();
        return response;
    }

    public String sayHelloHome() {
        GreetingEntity greeting = helloDao.sayHello();
        return greeting.message;
    }
}