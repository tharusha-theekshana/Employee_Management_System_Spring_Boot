package com.employee_management.employee_mn_system.Service.Impl;

import com.employee_management.employee_mn_system.Dto.EmployeeDto;
import com.employee_management.employee_mn_system.Dto.EmployeeResponseDto;
import com.employee_management.employee_mn_system.Entity.Department;
import com.employee_management.employee_mn_system.Entity.Employee;
import com.employee_management.employee_mn_system.Repo.DepartmentRepo;
import com.employee_management.employee_mn_system.Repo.EmployeeRepo;
import com.employee_management.employee_mn_system.Service.EmployeeService;
import com.employee_management.employee_mn_system.Utils.ResponseConstants;
import com.employee_management.employee_mn_system.Utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDto employeeDto) {
        try {
            if (validateDto(employeeDto)) {
                Employee employee = getEmployeeByDto(employeeDto);

                if (checkEmailIsExist(employee)){
                    if (checkDepartmentIsExist(employeeDto.getDepartmentId())){
                        employeeRepo.save(employee);
                        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.EMPLOYEE_SAVED_SUCCESSFULLY, HttpStatus.CREATED);

                    }else{
                        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.INVALID_DEPARTMENT, HttpStatus.BAD_REQUEST);
                    }
                }else{
                    return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.EMPLOYEE_EMAIL_EXISTS, HttpStatus.BAD_REQUEST);
                }
            } else {
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        try {

            List<EmployeeResponseDto> employeeList = employeeRepo.getAllEmployees();
            return ResponseMessages.getResponseEntityForList(ResponseConstants.FETCH_DATA_SUCCESSFULLY, employeeList, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForList(ResponseConstants.MESSAGE, new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateEmployee(Long id,EmployeeDto employeeDto) {
        try {
            if (validateDto(employeeDto)){
                Optional<Employee> employee = employeeRepo.findById(id);

                if (employee.isPresent()){
                    Employee existingEmployee = employee.get();
                    Employee updatedEmployee = getEmployeeByDto(employeeDto);

                    existingEmployee.setFirstName(updatedEmployee.getFirstName());
                    existingEmployee.setLastName(updatedEmployee.getLastName());
                    existingEmployee.setEmail(updatedEmployee.getEmail());
                    existingEmployee.setPhone(updatedEmployee.getPhone());
                    existingEmployee.setDepartment(updatedEmployee.getDepartment());

                    employeeRepo.save(existingEmployee);

                    return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.EMPLOYEE_UPDATED_SUCCESSFULLY, HttpStatus.OK);

                }else{
                    return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.EMPLOYEE_NOT_EXISTS, HttpStatus.NOT_FOUND);
                }
            }else{
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteEmployee(Long id) {
        try {
            Optional<Employee> employee = employeeRepo.findById(id);

            if (employee.isPresent()){
                employeeRepo.deleteById(id);
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.EMPLOYEE_DELETED_SUCCESSFULLY, HttpStatus.OK);

            }else{
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.EMPLOYEE_NOT_EXISTS, HttpStatus.NOT_FOUND);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllEmployeesByDepartment(Long depId) {
        try {

            List<EmployeeResponseDto> employeeList = employeeRepo.getAllEmployeesByDepartment(depId);
            return ResponseMessages.getResponseEntityForList(ResponseConstants.FETCH_DATA_SUCCESSFULLY, employeeList, HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForList(ResponseConstants.MESSAGE, new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAllEmployeeById(Long id) {
        try {

            EmployeeResponseDto employee = employeeRepo.getEmployeeById(id);

            if (!Objects.isNull(employee)){
                return ResponseMessages.getResponseEntityForOne(ResponseConstants.FETCH_DATA_SUCCESSFULLY, employee , HttpStatus.OK);
            }else{
                return ResponseMessages.getResponseEntityForOne(ResponseConstants.EMPLOYEE_NOT_EXISTS, null , HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForList(ResponseConstants.MESSAGE, new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean checkEmailIsExist(Employee employee) {
        Optional<Employee> employeeExist = employeeRepo.findByEmail(employee.getEmail());

        if (employeeExist.isEmpty()){
            return true;
        }
        return false;

    }

    private boolean validateDto(EmployeeDto employeeDto) {
        System.out.println(employeeDto.getLastName());
        if (employeeDto.getFirstName() != null && employeeDto.getLastName() != null && employeeDto.getEmail() != null && employeeDto.getPhone() != null && employeeDto.getDepartmentId() != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDepartmentIsExist(String id) {
        Optional<Department> department = departmentRepo.findById(Long.parseLong(id));
        System.out.println(department);

        if (!department.isEmpty()){
            return true;
        }
        return false;
    }

    private Employee getEmployeeByDto(EmployeeDto employeeDto) {
        Department department = new Department();
        department.setId(Long.parseLong(employeeDto.getDepartmentId()));

        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setDepartment(department);

        return employee;
    }
}