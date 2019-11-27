package com.abdul.empportal.api.rest.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RestEmployee extends RestEntity{

	private UUID id;

	@NotNull
	@Size(min = 1, max = 1000)
	private String lastname;

	@NotNull
	@Size(min = 1, max = 1000)
	private String firstname;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateofbirth;

	@NotNull
	@Size(min=1, max=7)
	private String gender;

	@NotNull
	@Size(min = 1, max = 1000)
	private String department;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
