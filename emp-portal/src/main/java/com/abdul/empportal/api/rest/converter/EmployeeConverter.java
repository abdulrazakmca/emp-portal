package com.abdul.empportal.api.rest.converter;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.rest.domain.RestEmployee;

public interface EmployeeConverter/* extends EntityConverter<Employee, RestEmployee> */{

	public RestEmployee convertToRest(Employee employee);
	public Employee convertToEntity(RestEmployee restEmployee);
	public Employee convertToExistingEntity(Employee entity, RestEmployee restEmployee);
}
