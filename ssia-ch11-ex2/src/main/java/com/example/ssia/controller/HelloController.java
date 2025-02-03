package com.example.ssia.controller;

import com.example.ssia.service.NameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final NameService nameService;

    public HelloController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/secret/names/{name}")
    public List<String> names(@PathVariable String name){
        return nameService.getSecretNames(name);
    }
}
