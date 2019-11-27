package com.abdul.empportal.api.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.abdul.empportal.api.domain.Employee;
import com.abdul.empportal.api.jpa.EmployeeRepository;

public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository repository;

	private static final UUID id = UUID.fromString("e2b2cb7a-b441-42f5-b71d-192e7d29558e");

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() {
		Employee e = createEmployee();
		Mockito.when(repository.save(e)).thenReturn(e);
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
