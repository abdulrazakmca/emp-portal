package com.abdul.empportal.api.rest.converter.impl;

import org.springframework.stereotype.Service;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.rest.converter.EmployeeConverter;
import com.abdul.empportal.api.rest.domain.RestEmployee;

@Service
public class EmployeeConverterImpl implements EmployeeConverter {

	public RestEmployee convertToRest(Employee employee) {
		RestEmployee createRestEmployee = createRestEmployee();
		createRestEmployee.setFirstname(employee.getFirstname());
		createRestEmployee.setLastname(employee.getLastname());
		createRestEmployee.setGender(employee.getGender());
		createRestEmployee.setDateofbirth(employee.getDateofbirth());
		createRestEmployee.setDepartment(employee.getDepartment());
		createRestEmployee.setId(employee.getId());
		return createRestEmployee;
	}

	public Employee convertToEntity(RestEmployee restEmployee) {
		Employee employee = createEmployee();
		employee.setFirstname(restEmployee.getFirstname());
		employee.setLastname(restEmployee.getLastname());
		employee.setGender(restEmployee.getGender());
		employee.setDateofbirth(restEmployee.getDateofbirth());
		employee.setDepartment(restEmployee.getDepartment());
		return employee;
	}

	private RestEmployee createRestEmployee() {
		return new RestEmployee();
	}

	private Employee createEmployee() {
		return new Employee();
	}

}
