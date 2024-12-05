package com.employee_management.employee_mn_system.Controller.Impl;

import com.employee_management.employee_mn_system.Controller.EmployeeController;
import com.employee_management.employee_mn_system.Dto.EmployeeDto;
import com.employee_management.employee_mn_system.Service.EmployeeService;
import com.employee_management.employee_mn_system.Utils.ResponseConstants;
import com.employee_management.employee_mn_system.Utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDto employeeDto) {
        try{
            return employeeService.createEmployee(employeeDto);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE,ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        try {
            return employeeService.getAllEmployees();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForList(ResponseConstants.MESSAGE,  new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateEmployee(Long id, EmployeeDto employeeDto) {
        try{
            return employeeService.updateEmployee(id,employeeDto);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE,ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> deleteEmployee(Long id) {
        try{
            return employeeService.deleteEmployee(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getEmployeeById(Long id) {
        try {
            return employeeService.getAllEmployeeById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForList(ResponseConstants.MESSAGE,  new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllEmployeesByDepartment(Long depId) {
        try {
            return employeeService.getAllEmployeesByDepartment(depId);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForList(ResponseConstants.MESSAGE,  new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
