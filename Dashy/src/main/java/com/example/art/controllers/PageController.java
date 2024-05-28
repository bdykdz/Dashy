package com.example.art.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/add-hours")
    public String addHoursPage() {
        return "add-hours"; // Thymeleaf template name without .html
    }
}
