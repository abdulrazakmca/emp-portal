package com.abdul.empportal.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

	public List<Employee> getListOfEmployee() {
		 Iterable<Employee> findAll = employeeRepository.findAll();
		 List<Employee> listOfEmployees = StreamSupport.stream(findAll.spliterator(), false).collect(Collectors.toList());
		 return listOfEmployees;
	}

	@Override
	public Employee findById(UUID id) {
		Optional<Employee> findById = employeeRepository.findById(id);
		return findById.isPresent()? findById.get():null;
	}

	public void deleteEmployee(Employee employeeEntity) {
		employeeRepository.delete(employeeEntity)	;
	}
}

