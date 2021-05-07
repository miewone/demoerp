package com.example.demoerp.domains.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    @GetMapping("/")
    public String hi()
    {
        return "home";
    }


    @ResponseBody
    @GetMapping("/api/hi")
    public String test()
    {
        return "test";
    }
}
