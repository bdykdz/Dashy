package com.example.art.controllers;

import com.example.art.models.Employee;
import com.example.art.models.Project;
import com.example.art.models.ProjectHoursLog;
import com.example.art.models.User;
import com.example.art.services.EmployeeService;
import com.example.art.services.ProjectHoursLogService;
import com.example.art.services.ProjectService;
import com.example.art.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectHoursLogService projectHoursLogService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/add-hours")
    public String addHoursForm(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        return "add-hours";
    }

    @PostMapping("/add-hours")
    public String addHours(@RequestParam Long employeeId,
                           @RequestParam Long projectId,
                           @RequestParam int hours,
                           @RequestParam int month,
                           @RequestParam int year,
                           Authentication authentication) {
        String projectManager = authentication.getName();
        User user = userService.findByUsername(projectManager);

        ProjectHoursLog log = new ProjectHoursLog();
        log.setEmployeeId(employeeId);
        log.setProjectId(projectId);
        log.setHours(hours);
        log.setMonth(month);
        log.setYear(year);
        log.setManager(projectManager);
        log.setUser(user);

        projectHoursLogService.saveProjectHoursLog(log);

        return "redirect:/user/dashboard";
    }

    @GetMapping("/download-excel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
        employeeService.downloadExcel(response.getOutputStream());
    }
}
