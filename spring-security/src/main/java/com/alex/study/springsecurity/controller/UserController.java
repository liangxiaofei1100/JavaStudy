package com.alex.study.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ResponseEntity<?> greeting() {

        return ResponseEntity.ok("Greeting from user");
    }
}
