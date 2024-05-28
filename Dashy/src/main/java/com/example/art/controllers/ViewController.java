package com.example.art.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/employee-dashboard")
    public String showEmployeeDashboard() {
        return "user/dashboard";  // This maps to src/main/resources/templates/user/dashboard.html
    }

    @GetMapping("/project-report")
    public String showProjectReport() {
        return "user/project_report";  // This maps to src/main/resources/templates/user/project_report.html
    }

    @GetMapping("/company-report")
    public String showCompanyReport() {
        return "user/company_report";  // Maps to src/main/resources/templates/user/company_report.html
    }
}
