package com.example1.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComponentScannerTestController {

	@GetMapping("/welcome/test")
	public String getWelcomeMessage() {
		return "testing component scanner";
	}
}
