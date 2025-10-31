package com.abdul.empportal.api.rest.service;

import java.util.UUID;

import javax.validation.Valid;

import com.abdul.empportal.api.rest.domain.RestEmployee;

public interface EmployeeRestService {

	RestEmployee createEmployee(RestEmployee e);

	RestEmployee getListOfEmployee();

	RestEmployee updateEmployee(@Valid RestEmployee restEmployee, UUID id);

	RestEmployee deleteEmployee(UUID id);




}
