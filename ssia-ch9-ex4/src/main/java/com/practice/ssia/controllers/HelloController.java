package com.practice.ssia.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            String identifier = request.getHeader("X-IDENTIFIER");
            if (identifier != null) {
                System.out.println("Token for " + identifier + " is " + token.getToken());
            }
        }
        return "Get Hello World";
    }

    @PostMapping("/post")
    public String post() {
        return "Post Hello World";
    }
}
