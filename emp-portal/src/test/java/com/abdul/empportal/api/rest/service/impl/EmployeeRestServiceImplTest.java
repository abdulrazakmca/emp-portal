package com.abdul.empportal.api.rest.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.rest.converter.EmployeeConverter;
import com.abdul.empportal.api.rest.domain.RestEmployee;
import com.abdul.empportal.api.service.EmployeeService;

public class EmployeeRestServiceImplTest {

	@Mock
	private EmployeeConverter employeeConverter;

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeRestServiceImpl employeeRestServiceImpl;

	private static final UUID id = UUID.fromString("e2b2cb7a-b441-42f5-b71d-192e7d29558e");

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateEmployee() {
		RestEmployee saveRestEmployee = createRestEmployee();
		Employee entityEmployee = createEmployee();
		Mockito.when(employeeConverter.convertToEntity(saveRestEmployee)).thenReturn(entityEmployee);
		Mockito.when(employeeService.save(entityEmployee)).thenReturn(entityEmployee);
		Mockito.when(employeeConverter.convertToRest(entityEmployee)).thenReturn(saveRestEmployee);
		RestEmployee createdEmployee = employeeRestServiceImpl.createEmployee(saveRestEmployee);
		assertNotNull(createdEmployee);
		Mockito.verify(employeeService, times(1)).save(entityEmployee);
	}

	@Test
	public void testGetListOfEmployee() {
		Employee entityEmployee = createEmployee();
		RestEmployee restEmployee = createRestEmployee();
		Mockito.when(employeeService.getListOfEmployee()).thenReturn(List.of(entityEmployee));
		Mockito.when(employeeConverter.convertToRest(entityEmployee)).thenReturn(restEmployee);
		RestEmployee listOfEmployee = employeeRestServiceImpl.getListOfEmployee();
		assertEquals(listOfEmployee.getListOfEmployee().size(), 1);
	}

	@Test
	public void testUpdateEmployee() {
		Employee entityEmployee = createEmployee();
		RestEmployee restEmployee = createRestEmployee();
		restEmployee.setFirstname("updateFirstName");
		;
		entityEmployee.setId(id);
		Mockito.when(employeeService.findById(id)).thenReturn(entityEmployee);
		Mockito.when(employeeConverter.convertToExistingEntity(entityEmployee, restEmployee))
				.thenReturn(entityEmployee);
		Mockito.when(employeeService.save(entityEmployee)).thenReturn(entityEmployee);
		Mockito.when(employeeConverter.convertToRest(entityEmployee)).thenReturn(restEmployee);

		RestEmployee updateEmployee = employeeRestServiceImpl.updateEmployee(restEmployee, id);

		assertEquals("updateFirstName", updateEmployee.getFirstname());
	}

	@Test
	public void testUpdateEmployeeNoRecord() {
		RestEmployee restEmployee = createRestEmployee();
		restEmployee.setFirstname("updateFirstName");
		Mockito.when(employeeService.findById(id)).thenReturn(null);
		RestEmployee updateEmployee = employeeRestServiceImpl.updateEmployee(restEmployee, id);
		assertEquals("Invalid Employee Record", updateEmployee.getErrorMessage());
	}
	
	
	@Test
	public void testDeleteEmployee() {
		Employee entityEmployee = createEmployee();
		entityEmployee.setId(id);
		Mockito.when(employeeService.findById(id)).thenReturn(entityEmployee);
		Mockito.doNothing().when(employeeService).deleteEmployee(entityEmployee);
		RestEmployee deleteEmployee = employeeRestServiceImpl.deleteEmployee(id);
		assertNotNull(deleteEmployee.getSuccessMessage());
		Mockito.verify(employeeService, times(1)).deleteEmployee(entityEmployee);
		
	}
	
	@Test
	public void testDeleteEmployee2() {
		Mockito.when(employeeService.findById(id)).thenReturn(null);
		RestEmployee deleteEmployee = employeeRestServiceImpl.deleteEmployee(id);
		assertNotNull(deleteEmployee.getErrorMessage());
		Mockito.verify(employeeService, times(1)).findById(id);
	}

	private RestEmployee createRestEmployee() {
		RestEmployee r = new RestEmployee();
		r.setFirstname("firstname");
		r.setLastname("lastname");
		r.setDateofbirth(LocalDate.of(2015, 01, 14));
		r.setGender("Male");
		return r;
	}

	private Employee createEmployee() {
		Employee e = new Employee();
		e.setFirstname("firstname");
		e.setLastname("lastname");
		e.setDateofbirth(LocalDate.of(2015, 01, 14));
		e.setGender("Male");
		return e;
	}
}
