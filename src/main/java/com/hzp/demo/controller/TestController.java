package com.hzp.demo.controller;

import com.hzp.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping(value = "/test")
    public String test() {
        return testService.test();
    }
}
