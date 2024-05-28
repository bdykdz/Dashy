package com.example.art.services;

import com.example.art.models.ProjectHoursLog;
import com.example.art.repositories.ProjectHoursLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectHoursLogService {

    @Autowired
    private ProjectHoursLogRepository projectHoursLogRepository;

    public void saveProjectHoursLog(ProjectHoursLog projectHoursLog) {
        projectHoursLogRepository.save(projectHoursLog);
    }

    public List<ProjectHoursLog> getLogsByEmployeeId(Long employeeId) {
        return projectHoursLogRepository.findByEmployee_Id(employeeId);
    }
    public List<ProjectHoursLog> getLogsByUserId(Long userId) {
        return projectHoursLogRepository.findByUserId(userId);
    }

    public List<ProjectHoursLog> getAllLogs() {
    }
}
