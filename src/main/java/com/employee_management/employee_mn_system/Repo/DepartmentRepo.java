package com.employee_management.employee_mn_system.Repo;

import com.employee_management.employee_mn_system.Dto.DepartmentResponseDto;
import com.employee_management.employee_mn_system.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

    @Query("SELECT new com.employee_management.employee_mn_system.Dto.DepartmentResponseDto(d.id,d.name) " +
            "FROM Department d where d.id = :id")
    DepartmentResponseDto getDepById(@Param("id")Long id);
}
