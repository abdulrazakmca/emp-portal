package com.abdul.empportal.api.service;

import java.util.List;
import java.util.UUID;

import com.abdul.empportal.api.domain.Employee;

public interface EmployeeService {

	public Employee save(Employee employee);

	public List<Employee> getListOfEmployee();

	public Employee findById(UUID id);

	public void deleteEmployee(Employee employeeEntity);
}
