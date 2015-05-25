package com.alex.javastudy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alex.javastudy.spring.resttemplate.RestTemplateDemo;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        RestTemplateDemo restTemplateDemo = new RestTemplateDemo();
        restTemplateDemo.launch();
    }
}