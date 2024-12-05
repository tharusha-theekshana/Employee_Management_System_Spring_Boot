package com.employee_management.employee_mn_system.Service;

import com.employee_management.employee_mn_system.Dto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EmployeeService {
    ResponseEntity<String> createEmployee(EmployeeDto employeeDto);
    ResponseEntity<Map<String, Object>> getAllEmployees();
    ResponseEntity<String> updateEmployee(Long id,EmployeeDto employeeDto);
    ResponseEntity<String> deleteEmployee(Long id);
    ResponseEntity<Map<String, Object>> getAllEmployeesByDepartment(Long depId);
    ResponseEntity<Map<String, Object>> getAllEmployeeById(Long id);
}
