package com.employee_management.employee_mn_system.Service;

import com.employee_management.employee_mn_system.Dto.DepartmentDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DepartmentService {
    ResponseEntity<String> createDepartment(DepartmentDto departmentDto);
    ResponseEntity<Map<String, Object>> getDepartmentById(Long id);
    ResponseEntity<String> updateDepartment(Long id, DepartmentDto departmentDto);
    ResponseEntity<String> deleteDepartment(Long id);
}
