package com.abdul.empportal.api.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.empportal.api.rest.domain.RestEmployee;
import com.abdul.empportal.api.rest.service.EmployeeRestService;
import com.abdul.empportal.api.rest.util.RestEntityErrorMessageAppender;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeRestService employeeRestService;

	@Autowired
	private RestEntityErrorMessageAppender errorMessageAppender;
	
	@PostMapping("/registration")
	public ResponseEntity<RestEmployee> saveEmployee(@RequestBody @Valid RestEmployee restEmployee, BindingResult binding) {
		if(binding.hasErrors()) {
			return errorMessageAppender.append(restEmployee, binding);
		}
		RestEmployee createdEmployee = employeeRestService.saveEmployee(restEmployee);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

}
