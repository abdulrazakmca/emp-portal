package com.abdul.empportal.api.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.rest.converter.EmployeeConverter;
import com.abdul.empportal.api.rest.domain.RestEmployee;
import com.abdul.empportal.api.rest.service.EmployeeRestService;
import com.abdul.empportal.api.service.EmployeeService;

@Service
public class EmployeeRestServiceImpl implements EmployeeRestService {

	@Autowired
	private EmployeeConverter employeeConverter;

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public RestEmployee saveEmployee(RestEmployee restEmployee) {
		Employee convertToEntity = employeeConverter.convertToEntity(restEmployee);
		return employeeConverter.convertToRest(employeeService.save(convertToEntity));
	}

	
}
