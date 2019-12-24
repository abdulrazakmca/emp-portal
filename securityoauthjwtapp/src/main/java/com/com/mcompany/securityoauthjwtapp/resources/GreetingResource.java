package com.com.mcompany.securityoauthjwtapp.resources;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingResource {
	
	@GetMapping("/hello")
	public String hello(Principal principal) {
		return "Hello, " + principal.getName();
	}
	@GetMapping("/my")
	public ResponseEntity<Principal> showme(Principal principal) {
		return ResponseEntity.ok(principal);
	}

}
