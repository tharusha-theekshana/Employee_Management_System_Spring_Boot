package com.employee_management.employee_mn_system.Service.Impl;

import com.employee_management.employee_mn_system.Dto.DepartmentDto;
import com.employee_management.employee_mn_system.Dto.DepartmentResponseDto;
import com.employee_management.employee_mn_system.Dto.EmployeeDto;
import com.employee_management.employee_mn_system.Entity.Department;
import com.employee_management.employee_mn_system.Entity.Employee;
import com.employee_management.employee_mn_system.Repo.DepartmentRepo;
import com.employee_management.employee_mn_system.Service.DepartmentService;
import com.employee_management.employee_mn_system.Utils.ResponseConstants;
import com.employee_management.employee_mn_system.Utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public ResponseEntity<String> createDepartment(DepartmentDto departmentDto) {
        try {

            Department department = getDepartmentByDto(departmentDto);
            departmentRepo.save(department);

            return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.DEPARTMENT_SAVED_SUCCESSFULLY, HttpStatus.CREATED);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDepartmentById(Long id) {
        try {

            DepartmentResponseDto department = departmentRepo.getDepById(id);

            if (department != null) {
                return ResponseMessages.getResponseEntityForOneDep(ResponseConstants.FETCH_DATA_SUCCESSFULLY, department, HttpStatus.OK);

            } else {
                return ResponseMessages.getResponseEntityForOneDep(ResponseConstants.DEPARTMENT_NOT_EXISTS, null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForListDep(ResponseConstants.MESSAGE, new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateDepartment(Long id, DepartmentDto departmentDto) {
        try {
            if (validateDto(departmentDto)) {
                Optional<Department> department = departmentRepo.findById(id);

                if (department.isPresent()) {
                    Department existingDepartment = department.get();
                    Department updatedDepartment = getDepartmentByDto(departmentDto);

                    existingDepartment.setName(updatedDepartment.getName());

                    departmentRepo.save(existingDepartment);

                    return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.DEPARTMENT_UPDATED_SUCCESSFULLY, HttpStatus.OK);

                } else {
                    return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.DEPARTMENT_NOT_EXISTS, HttpStatus.NOT_FOUND);
                }
            } else {
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.INVALID_DATA_DEP, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteDepartment(Long id) {
        try {
            Optional<Department> department = departmentRepo.findById(id);

            if (department.isPresent()){
                departmentRepo.deleteById(id);
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.DEPARTMENT_DELETED_SUCCESSFULLY, HttpStatus.OK);

            }else{
                return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.DEPARTMENT_NOT_EXISTS, HttpStatus.NOT_FOUND);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE, ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateDto(DepartmentDto departmentDto) {
        if (departmentDto.getName() != null) {
            return true;
        } else {
            return false;
        }
    }


    private Department getDepartmentByDto(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());

        return department;
    }
}
