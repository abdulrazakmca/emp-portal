package com.abdul.empportal.api.rest.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.empportal.api.rest.domain.RestEmployee;
import com.abdul.empportal.api.rest.service.EmployeeRestService;
import com.abdul.empportal.api.rest.util.RestEntityErrorMessageAppender;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeRestService employeeRestService;

	@Autowired
	private RestEntityErrorMessageAppender errorMessageAppender;
	
	@PostMapping("/create")
	public ResponseEntity<RestEmployee> createEmployee(@RequestBody @Valid RestEmployee restEmployee, BindingResult binding) {
		if(binding.hasErrors()) {
			return errorMessageAppender.append(restEmployee, binding);
		}
		RestEmployee createdEmployee = employeeRestService.createEmployee(restEmployee);
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping(path="/employees")
	public ResponseEntity<RestEmployee>  getListOfEmployee(){
		return new ResponseEntity<>(employeeRestService.getListOfEmployee(), HttpStatus.OK);
	}
	
	@PutMapping(path="/employee/{id}")
	public ResponseEntity<RestEmployee>  updateEmloyee(@RequestBody @Valid RestEmployee restEmployee,@PathVariable UUID id,BindingResult binding){
		if(binding.hasErrors()) {
			return errorMessageAppender.append(restEmployee, binding);
		}
		RestEmployee updateEmployee = employeeRestService.updateEmployee(restEmployee,id);
		 if(updateEmployee.getErrorMessage()!= null) {
			return new ResponseEntity<>(updateEmployee, HttpStatus.NO_CONTENT);
		 }
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/employee/{id}")
	public ResponseEntity<RestEmployee>  deleteEmloyee(@PathVariable UUID id){
		RestEmployee deleteEmployee = employeeRestService.deleteEmployee(id);
		 if(deleteEmployee.getErrorMessage()!= null) {
			return new ResponseEntity<>(deleteEmployee, HttpStatus.NO_CONTENT);
		 }
		return new ResponseEntity<>(deleteEmployee, HttpStatus.OK);
	}
	

}
