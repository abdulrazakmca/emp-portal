package com.abdul.empportal.api.rest.service.impl;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
	public RestEmployee createEmployee(RestEmployee restEmployee) {
		Employee convertToEntity = employeeConverter.convertToEntity(restEmployee);
		return employeeConverter.convertToRest(employeeService.save(convertToEntity));
	}

	@Override
	public RestEmployee getListOfEmployee() {
		List<Employee> listOfEmployees = employeeService.getListOfEmployee();
	    List<RestEmployee> collect = listOfEmployees.stream().map(employeeConverter::convertToRest).collect(Collectors.toList());
	    RestEmployee restEmployee=new RestEmployee();
	    restEmployee.setListOfEmployee(collect);
	    return restEmployee;
	}

	@Override
	public RestEmployee updateEmployee(@Valid RestEmployee restEmployee, UUID id) {
		Employee employeeEntity = employeeService.findById(id);
		if(isNull(employeeEntity)) {
			restEmployee = new RestEmployee();
			restEmployee.setErrorMessage("Invalid Employee Record");
			return restEmployee;
		}
		Employee convertToExistingEntity = employeeConverter.convertToExistingEntity(employeeEntity, restEmployee);
		Employee updatedEntity = employeeService.save(convertToExistingEntity);
		return employeeConverter.convertToRest(updatedEntity);
	}

	@Override
	public RestEmployee deleteEmployee(UUID id) {
		Employee employeeEntity = employeeService.findById(id);
		RestEmployee restEmployee = new RestEmployee();
		if(isNull(employeeEntity)) {
			restEmployee.setErrorMessage("Invalid Employee Record "+id);
			return restEmployee;
		}
		employeeService.deleteEmployee(employeeEntity);
		restEmployee.setSuccessMessage("Id "+id+" Successfully Deleted");
		return restEmployee;
	}

	
}
