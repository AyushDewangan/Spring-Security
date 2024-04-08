package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer){
		Customer savedCustomer = null;
		ResponseEntity response = null;
		
		
		try {
			// BCrypting the password(hashing)
			String hashedPassword = passwordEncoder.encode(customer.getPwd());
			
			customer.setPwd(hashedPassword);
			savedCustomer = customerRepository.save(customer);
		if(savedCustomer != null)
			response = ResponseEntity
			.status(HttpStatus.CREATED)
			.body("Customer registered successfully.");
		}
		catch(Exception e) {
			response = ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body("Having issue while registering customer.\n Stack-Trace : " + e.getStackTrace() + "\nReasone : " + e.getMessage());
		}
		return response;
	}
}
