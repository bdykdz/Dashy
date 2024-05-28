package com.example.art.services;

import com.example.art.models.Employee;
import com.example.art.models.Project;
import com.example.art.repositories.EmployeeRepository;
import com.example.art.repositories.ProjectRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    private ProjectRepository projectRepository;  // Assuming you have a repository for handling projects

    public void addHoursToProject(Long employeeId, String projectName, int hours, String month, int year, String projectManager) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        Project project = new Project();
        project.setEmployee(employee);
        project.setName(projectName);
        project.setHours(hours);
        project.setMonth(month);
        project.setYear(year);
        project.setManager(projectManager);
        projectRepository.save(project);
    }

    public void downloadExcel(OutputStream outputStream) throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");

            int rowNum = 0;
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Position");
            headerRow.createCell(2).setCellValue("Hours Allocated");
            headerRow.createCell(3).setCellValue("Vacation Hours");
            headerRow.createCell(4).setCellValue("Medical Hours");
            headerRow.createCell(5).setCellValue("Project Allocation Change");
            headerRow.createCell(6).setCellValue("Cleaned Task Ratio");
            headerRow.createCell(7).setCellValue("Achieved Tasks");

            for (Employee employee : employees) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(employee.getName());
                row.createCell(1).setCellValue(employee.getPosition());
                row.createCell(2).setCellValue(employee.getHoursAllocated());
                row.createCell(3).setCellValue(employee.getVacationHours());
                row.createCell(4).setCellValue(employee.getMedicalHours());
                row.createCell(5).setCellValue(employee.getProjectAllocationChange());
                row.createCell(6).setCellValue(employee.getCleanedTaskRatio());
                row.createCell(7).setCellValue(employee.getAchievedTasks());
            }

            workbook.write(outputStream);
        }
    }
}
