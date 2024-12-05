package com.employee_management.employee_mn_system.Repo;

import com.employee_management.employee_mn_system.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {
}
