package com.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping({"/login", "/register", "/dashboard/**"})
    public String forward() {
        return "forward:/index.html";
    }
}
