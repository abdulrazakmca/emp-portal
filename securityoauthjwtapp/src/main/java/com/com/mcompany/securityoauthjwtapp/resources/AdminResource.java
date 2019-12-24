package com.com.mcompany.securityoauthjwtapp.resources;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminResource {

	@GetMapping("/hello")
	//@Secured({"ROLE_ADMIN"})
//	@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String hello(Principal principal) {
		return "Admin REST. Hello, " + principal.getName();
	}
}
