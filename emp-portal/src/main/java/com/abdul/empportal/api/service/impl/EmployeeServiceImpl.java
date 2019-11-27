package com.abdul.empportal.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

	@Override
	public List<Employee> findAllEmployee() {
		return StreamSupport.stream(employeeRepository.findAll().spliterator(), false) 
	            .collect(Collectors.toList()); 
	}
}

