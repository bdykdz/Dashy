package com.example.art.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position;
    private int hoursAllocated;
    private int vacationHours;
    private int medicalHours;
    private int projectAllocationChange;
    private int cleanedTaskRatio;
    private int achievedTasks;

    // Default constructor
    public Employee() {}

    // Constructor with id
    public Employee(Long id) {
        this.id = id;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHoursAllocated() {
        return hoursAllocated;
    }

    public void setHoursAllocated(int hoursAllocated) {
        this.hoursAllocated = hoursAllocated;
    }

    public int getVacationHours() {
        return vacationHours;
    }

    public void setVacationHours(int vacationHours) {
        this.vacationHours = vacationHours;
    }

    public int getMedicalHours() {
        return medicalHours;
    }

    public void setMedicalHours(int medicalHours) {
        this.medicalHours = medicalHours;
    }

    public int getProjectAllocationChange() {
        return projectAllocationChange;
    }

    public void setProjectAllocationChange(int projectAllocationChange) {
        this.projectAllocationChange = projectAllocationChange;
    }

    public int getCleanedTaskRatio() {
        return cleanedTaskRatio;
    }

    public void setCleanedTaskRatio(int cleanedTaskRatio) {
        this.cleanedTaskRatio = cleanedTaskRatio;
    }

    public int getAchievedTasks() {
        return achievedTasks;
    }

    public void setAchievedTasks(int achievedTasks) {
        this.achievedTasks = achievedTasks;
    }
}
