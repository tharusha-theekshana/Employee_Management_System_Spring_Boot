package com.employee_management.employee_mn_system.Controller;

import com.employee_management.employee_mn_system.Dto.DepartmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("api/v1/dep")
public interface DepartmentController{
    @PostMapping
    ResponseEntity<String> createDepartment(@RequestBody(required = true) DepartmentDto departmentDto);

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getDepById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<String> updateDep(@PathVariable Long id,@RequestBody(required = true) DepartmentDto departmentDto);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteDep(@PathVariable Long id);

}
