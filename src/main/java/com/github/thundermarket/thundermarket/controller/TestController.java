package com.github.thundermarket.thundermarket.controller;

import com.github.thundermarket.thundermarket.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/greeting")
    public @ResponseBody String greeting() {
        return testService.greet();
    }
}
