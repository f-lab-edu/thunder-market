package com.github.thundermarket.thundermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/greeting")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}
