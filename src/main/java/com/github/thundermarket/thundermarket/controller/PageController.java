package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.aspect.Authenticated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Authenticated
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
