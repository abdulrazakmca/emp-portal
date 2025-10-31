package com.abdul.empportal.api.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.jpa.EmployeeRepository;

public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository repository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	private static final UUID id = UUID.fromString("e2b2cb7a-b441-42f5-b71d-192e7d29558e");

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() {
		Employee e = createEmployee();
		Mockito.when(repository.save(e)).thenReturn(e);
		e = employeeServiceImpl.save(e);
		assertNotNull(1);
		Mockito.verify(repository, times(1)).save(e);
	}
	
	@Test
	public void testGetListOfEmployee() {
		List<Employee> listOfEmployee=List.of(createEmployee());
		Mockito.when(repository.findAll()).thenReturn(listOfEmployee);
		List<Employee> list = employeeServiceImpl.getListOfEmployee();
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testfindById() {
		Employee createEmployee = createEmployee();
		Optional<Employee> of = Optional.of(createEmployee);
		Mockito.when(repository.findById(id)).thenReturn(of);
		Employee findEmployee = employeeServiceImpl.findById(id);
		assertNotNull(findEmployee);
	}
	
	@Test
	public void testfindById2() {
		Employee createEmployee = createEmployee();
		Optional<Employee> of = Optional.of(createEmployee);
		Mockito.when(repository.findById(id)).thenReturn(of);
		Employee findEmployee = employeeServiceImpl.findById(UUID.randomUUID());
		assertEquals(findEmployee,null);
	}
	
	@Test
	public void testdeleteEmployee() {
		Employee employeeEntity = createEmployee();
		employeeServiceImpl.deleteEmployee(employeeEntity);
		 Mockito.verify(repository, times(1)).delete(employeeEntity);
	}

	
	
	private Employee createEmployee() {
		Employee e = new Employee();
		e.setId(id);
		e.setFirstname("firstname");
		e.setLastname("lastname");
		e.setDateofbirth(LocalDate.of(2015, 01, 14));
		e.setGender("Male");
		return e;
	}

}
