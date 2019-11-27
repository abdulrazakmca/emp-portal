package com.abdul.empportal.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.jpa.EmployeeRepository;
import com.abdul.empportal.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee save(Employee employee) {
	    return employeeRepository.save(employee);
	  }
}

