package com.abdul.empportal.api.util;

import java.time.LocalDate;
import java.util.UUID;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.rest.domain.RestEmployee;

public class EmployeeInstanceUtil {

	public static Employee createEntityInstance() {
		Employee employee = new Employee();
		employee.setFirstname("firstname");
		employee.setLastname("lastname");
		employee.setDateofbirth(LocalDate.of(2015, 01, 14));
		employee.setGender("Female");
		employee.setDepartment("police");
		return employee;

	}
	
	public static RestEmployee createRestInstance() {
		RestEmployee employee = new RestEmployee();
		employee.setFirstname("firstname");
		employee.setLastname("lastname");
		employee.setDateofbirth(LocalDate.of(2015, 01, 14));
		employee.setGender("Female");
		employee.setDepartment("police");
		return employee;

	}
	
	public static UUID uuid(String uuid) {
		return UUID.fromString(uuid);
	}


}
