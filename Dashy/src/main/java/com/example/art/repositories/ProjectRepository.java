package com.example.art.repositories;

import com.example.art.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByEmployeeId(Long employeeId);
}
