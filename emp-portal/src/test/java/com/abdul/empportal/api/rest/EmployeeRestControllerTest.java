package com.abdul.empportal.api.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.abdul.empportal.api.rest.controller.EmployeeRestController;
import com.abdul.empportal.api.rest.domain.RestEmployee;
import com.abdul.empportal.api.rest.service.EmployeeRestService;
import com.abdul.empportal.api.util.EmployeeInstanceUtil;

public class EmployeeRestControllerTest {
	
	@InjectMocks
	private EmployeeRestController impl;

	@Mock
	private EmployeeRestService service;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveEmployee() {
		RestEmployee restEmployee=EmployeeInstanceUtil.createRestInstance();
		RestEmployee savedEmployee=EmployeeInstanceUtil.createRestInstance();
		savedEmployee.setId(EmployeeInstanceUtil.uuid("f570d846-6278-459a-a68f-1ab8f7093350"));
		when(service.saveEmployee(restEmployee)).thenReturn(savedEmployee);
		final ResponseEntity<RestEmployee> response = impl.saveEmployee(restEmployee, mock(BindingResult.class));
		assertThat(response.getBody()).isEqualTo(savedEmployee);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
	
	@Test
	public void findAllEmployee() {
		RestEmployee e1=EmployeeInstanceUtil.createRestInstance();
		e1.setId(EmployeeInstanceUtil.uuid("f570d846-6278-459a-a68f-1ab8f7093350"));
		RestEmployee e2=EmployeeInstanceUtil.createRestInstance();
		e2.setId(EmployeeInstanceUtil.uuid("f570d846-6278-459a-a68f-1ab8f7093351"));
		List<RestEmployee> of = List.of(e1, e2);
		when(service.findAllRestEmployee()).thenReturn(of);
		final ResponseEntity<List<RestEmployee>> response = impl.findAllEmployee();
		assertEquals(response.getBody().size(), 2);
			
	}
}
