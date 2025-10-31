package com.abdul.empportal.api.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.abdul.empportal.api.rest.domain.RestEmployee;
import com.abdul.empportal.api.rest.service.EmployeeRestService;
import com.abdul.empportal.api.rest.util.RestEntityErrorMessageAppender;

public class EmployeeRestControllerTest {

	@Mock
	private EmployeeRestService employeeRestService;

	@Mock
	private RestEntityErrorMessageAppender errorMessageAppender;

	@InjectMocks
	private EmployeeRestController employeeRestControllerImpl;

	private static final UUID id = UUID.fromString("e2b2cb7a-b441-42f5-b71d-192e7d29558e");

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * if(binding.hasErrors()) { return errorMessageAppender.append(restEmployee,
	 * binding); } RestEmployee createdEmployee =
	 * employeeRestService.createEmployee(restEmployee); return new
	 * ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	 */

	/*
	 * final ResponseEntity<RestRoute> response = impl.getRoute(routeId);
	 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	 * verify(routeRestService).get(routeId);
	 * verifyNoMoreInteractions(routeRestService);
	 */

	@Test
	public void testCreateEmployee() {
		RestEmployee createdRestEmployee = createRestEmployee();
		createdRestEmployee.setId(id);
		RestEmployee restEmployee = createRestEmployee();
		Mockito.when(employeeRestService.createEmployee(restEmployee)).thenReturn(createdRestEmployee);
		ResponseEntity<RestEmployee> response = employeeRestControllerImpl.createEmployee(restEmployee,
				mock(BindingResult.class));
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
	}

	;

	@Test
	public void testGetListOfEmployee() {
		RestEmployee createRestEmployee = new RestEmployee();
		List<RestEmployee> of = List.of(createRestEmployee);
		createRestEmployee.setListOfEmployee(of);
		Mockito.when(employeeRestService.getListOfEmployee()).thenReturn(createRestEmployee);
		ResponseEntity<RestEmployee> response = employeeRestControllerImpl.getListOfEmployee();
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdateEmloyee() {
		RestEmployee restEmployee = createRestEmployee();
		restEmployee.setId(id);
		Mockito.when(employeeRestService.updateEmployee(restEmployee, id)).thenReturn(restEmployee);
		ResponseEntity<RestEmployee> response = employeeRestControllerImpl.updateEmloyee(restEmployee, id,
				mock(BindingResult.class));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	
	@Test
	public void testUpdateEmloyee2() {
		RestEmployee restEmployee = new RestEmployee();
		restEmployee.setErrorMessage("No_Content");
		Mockito.when(employeeRestService.updateEmployee(restEmployee, id)).thenReturn(restEmployee);	
		ResponseEntity<RestEmployee> response = employeeRestControllerImpl.updateEmloyee(restEmployee, id,
				mock(BindingResult.class));
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
	@Test
	public void testDeleteEmloyee() {
		RestEmployee restEmployee = new RestEmployee();
		restEmployee.setSuccessMessage("success");
		Mockito.when(employeeRestService.deleteEmployee(id)).thenReturn(restEmployee);	
		ResponseEntity<RestEmployee> response = employeeRestControllerImpl.deleteEmloyee(id);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testDeleteEmloyee2() {
		RestEmployee restEmployee = new RestEmployee();
		restEmployee.setErrorMessage("No_Content");
		Mockito.when(employeeRestService.deleteEmployee(id)).thenReturn(restEmployee);	
		ResponseEntity<RestEmployee> response = employeeRestControllerImpl.deleteEmloyee(id);
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}

	private RestEmployee createRestEmployee() {
		RestEmployee r = new RestEmployee();
		r.setFirstname("firstname");
		r.setLastname("lastname");
		r.setDateofbirth(LocalDate.of(2015, 01, 14));
		r.setGender("Male");
		return r;
	}

}
