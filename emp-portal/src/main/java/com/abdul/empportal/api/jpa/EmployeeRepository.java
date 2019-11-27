package com.abdul.empportal.api.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abdul.empportal.api.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID>{

}
