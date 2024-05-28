package com.example.art.controllers;

import com.example.art.models.ProjectHoursLog;
import com.example.art.models.User;
import com.example.art.services.ProjectHoursLogService;
import com.example.art.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-hours-log")
public class ProjectHoursLogController {

    @Autowired
    private ProjectHoursLogService projectHoursLogService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ProjectHoursLog> getLogsByEmployeeId(@RequestParam Long employeeId) {
        return projectHoursLogService.getLogsByEmployeeId(employeeId);
    }

    @PostMapping("/add")
    public String addProjectHoursLog(@RequestParam Long employeeId,
                                     @RequestParam Long projectId,
                                     @RequestParam int hours,
                                     @RequestParam int month,
                                     @RequestParam int year,
                                     Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        ProjectHoursLog log = new ProjectHoursLog();
        log.setEmployeeId(employeeId);
        log.setProjectId(projectId);
        log.setHours(hours);
        log.setMonth(month);
        log.setYear(year);
        log.setManager(username);
        log.setUser(user);

        projectHoursLogService.saveProjectHoursLog(log);

        return "redirect:/user/dashboard";
    }
}
