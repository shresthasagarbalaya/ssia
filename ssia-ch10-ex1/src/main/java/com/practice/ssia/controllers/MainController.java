package com.practice.ssia.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String index() {
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
    @CrossOrigin("http://localhost:8080")
    public String test() {
        logger.info("Test method called");
        return "test";
    }

}
