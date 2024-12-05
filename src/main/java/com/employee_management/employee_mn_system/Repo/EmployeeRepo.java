package com.employee_management.employee_mn_system.Repo;

import com.employee_management.employee_mn_system.Dto.EmployeeResponseDto;
import com.employee_management.employee_mn_system.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);

    @Query("SELECT new com.employee_management.employee_mn_system.Dto.EmployeeResponseDto(e.firstName,e.lastName,e.email,e.phone,e.department.id,e.department.name) " +
            "FROM Employee e")
    List<EmployeeResponseDto> getAllEmployees();
    @Query("SELECT new com.employee_management.employee_mn_system.Dto.EmployeeResponseDto(e.firstName,e.lastName,e.email,e.phone,e.department.id,e.department.name) " +
            "FROM Employee e where e.department.id = :depId")
    List<EmployeeResponseDto> getAllEmployeesByDepartment(@Param("depId")Long depId);

    @Query("SELECT new com.employee_management.employee_mn_system.Dto.EmployeeResponseDto(e.firstName,e.lastName,e.email,e.phone,e.department.id,e.department.name) " +
            "FROM Employee e where e.id = :id")
    EmployeeResponseDto getEmployeeById(@Param("id")Long id);
}
