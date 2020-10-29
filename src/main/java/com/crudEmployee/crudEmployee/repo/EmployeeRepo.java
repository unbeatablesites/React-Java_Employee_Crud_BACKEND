package com.crudEmployee.crudEmployee.repo;

import com.crudEmployee.crudEmployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
