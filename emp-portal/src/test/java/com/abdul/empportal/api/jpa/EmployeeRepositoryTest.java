package com.abdul.empportal.api.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abdul.empportal.api.domain.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@PersistenceContext
	private EntityManager em;

	private Employee employee, employee1;

	@Before
	public void before() {
		employee = new Employee();
		employee.setFirstname("firstname");
		employee.setLastname("lastname");
		employee.setDateofbirth(LocalDate.of(2015, 01, 14));
		employee.setGender("Female");

		employee1 = new Employee();
		employee1.setFirstname("firstname");
		employee1.setLastname("lastname");
		employee1.setDateofbirth(LocalDate.of(2015, 01, 14));
		employee1.setGender("Male");

		employeeRepository.save(employee);
		employeeRepository.save(employee1);
		em.flush();
		em.clear();
	}

	@Test
	public void testFindAll() {
		Iterable<Employee> findAll = employeeRepository.findAll();
		long count = StreamSupport.stream(findAll.spliterator(), false).count();
		//assertEquals(count, 6);
		
	}
	
	@After
	public void after() {
		employeeRepository.delete(employee);
		employeeRepository.delete(employee1);
		Iterable<Employee> findAll = employeeRepository.findAll();
		long count = StreamSupport.stream(findAll.spliterator(), false).count();
		//assertEquals(3, count);
	}

}
