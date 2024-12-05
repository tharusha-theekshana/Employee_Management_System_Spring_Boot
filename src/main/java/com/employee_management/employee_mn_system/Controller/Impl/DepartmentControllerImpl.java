package com.employee_management.employee_mn_system.Controller.Impl;

import com.employee_management.employee_mn_system.Controller.DepartmentController;
import com.employee_management.employee_mn_system.Dto.DepartmentDto;
import com.employee_management.employee_mn_system.Service.DepartmentService;
import com.employee_management.employee_mn_system.Utils.ResponseConstants;
import com.employee_management.employee_mn_system.Utils.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class DepartmentControllerImpl implements DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public ResponseEntity<String> createDepartment(DepartmentDto departmentDto) {
        try{
            return departmentService.createDepartment(departmentDto);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE,ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDepById(Long id) {
        try {
            return departmentService.getDepartmentById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntityForListDep(ResponseConstants.MESSAGE,  new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateDep(Long id, DepartmentDto departmentDto) {
        try{
            return departmentService.updateDepartment(id,departmentDto);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE,ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteDep(Long id) {
        try{
            return departmentService.deleteDepartment(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return ResponseMessages.getResponseEntity(ResponseConstants.MESSAGE,ResponseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
