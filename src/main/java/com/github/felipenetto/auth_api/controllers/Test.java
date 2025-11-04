package com.github.felipenetto.auth_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
    @GetMapping()
    public String test() {
        return "caiu aqui em";
    }

    @PostMapping
    public String testPost() {
        return "caiu aqui em post";
    }
}
