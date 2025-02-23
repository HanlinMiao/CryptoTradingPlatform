package com.hanlin.TradingPlatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "Welcome to Trading Platform!";
    }

    @GetMapping("/api")
    public String secure() {
        return "Welcome to Trading Platform Secure!";
    }
}
