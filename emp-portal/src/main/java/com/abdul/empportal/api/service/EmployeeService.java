package com.abdul.empportal.api.service;

import java.util.List;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.rest.domain.RestEmployee;

public interface EmployeeService {

	public Employee save(Employee employee);
	
	List<Employee> findAllEmployee();
	
}
