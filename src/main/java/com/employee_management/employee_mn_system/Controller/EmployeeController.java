package com.employee_management.employee_mn_system.Controller;

import com.employee_management.employee_mn_system.Dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "api/v1/employees")
public interface EmployeeController {

    @PostMapping
    ResponseEntity<String> createEmployee(@RequestBody(required = true)EmployeeDto employeeDto);

    @GetMapping
    ResponseEntity<Map<String, Object>> getAllEmployees();

    @PutMapping("/{id}")
    ResponseEntity<String> updateEmployee(@PathVariable Long id,@RequestBody(required = true)EmployeeDto employeeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id);

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id);

    @GetMapping("/getByDep")
    ResponseEntity<Map<String, Object>> getAllEmployeesByDepartment(@RequestParam Long depId);

}
