package com.employee_management.employee_mn_system.Dto;

import com.employee_management.employee_mn_system.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class DepartmentResponseDto {

    private Long id;
    private String name;

    public DepartmentResponseDto() {
    }

    public DepartmentResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

}
