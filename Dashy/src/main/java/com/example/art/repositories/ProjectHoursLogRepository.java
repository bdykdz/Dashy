package com.example.art.repositories;

import com.example.art.models.ProjectHoursLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectHoursLogRepository extends JpaRepository<ProjectHoursLog, Long> {
    List<ProjectHoursLog> findByEmployee_Id(Long employeeId);
    List<ProjectHoursLog> findByUserId(Long userId);
}
