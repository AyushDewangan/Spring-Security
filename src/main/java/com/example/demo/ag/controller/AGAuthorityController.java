package com.example.demo.ag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ag.model.AGAuthority;
import com.example.demo.ag.repository.AGAuthorityRepository;

@RestController
@RequestMapping("/ag")
public class AGAuthorityController {

	@Autowired
	private AGAuthorityRepository agAuthorityRepository;
	
	@PostMapping("/save/authority")
	public AGAuthority saveAuthority(@RequestBody AGAuthority authority) {
		return agAuthorityRepository.save(authority);
	}
}
