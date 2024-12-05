package com.employee_management.employee_mn_system.Utils;


import com.employee_management.employee_mn_system.Dto.EmployeeDto;
import com.employee_management.employee_mn_system.Dto.EmployeeResponseDto;
import com.employee_management.employee_mn_system.Entity.Employee;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
public class ResponseMessages {
    public static ResponseEntity<String> getResponseEntity(String messageTitle, String message, HttpStatus httpStatus) {
        String responseBody = String.format(
                "{\n" +
                        "    \"Status\": %d,\n" +
                        "    \"Reason\": \"%s\",\n" +
                        "    \"%s\": \"%s\"\n" +
                        "}",
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                messageTitle,
                message
        );
        return new ResponseEntity<>(responseBody, httpStatus);
    }

    public static ResponseEntity<Map<String, Object>> getResponseEntityForList(String message, List<EmployeeResponseDto> employeeList, HttpStatus httpStatus) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Status", httpStatus.value());
        responseBody.put("Message", message);
        responseBody.put("Data", employeeList);

        return new ResponseEntity<>(responseBody, httpStatus);
    }

    public static ResponseEntity<Map<String, Object>> getResponseEntityForOne(String message, EmployeeResponseDto employee, HttpStatus httpStatus) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Status", httpStatus.value());
        responseBody.put("Message", message);
        responseBody.put("Data", employee);

        return new ResponseEntity<>(responseBody, httpStatus);
    }
}
