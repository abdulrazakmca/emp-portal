package com.abdul.empportal.api.rest.service;

import java.util.List;

import com.abdul.empportal.api.rest.domain.RestEmployee;

public interface EmployeeRestService {

	RestEmployee saveEmployee(RestEmployee e);
	
	List<RestEmployee> findAllRestEmployee();
}
