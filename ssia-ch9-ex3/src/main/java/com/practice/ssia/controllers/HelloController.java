package com.practice.ssia.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public String hello() {
        return "Post Hello World";
    }

    @PostMapping("/ciao")
    public String ciao() {
        return "Post ciao World";
    }

}
