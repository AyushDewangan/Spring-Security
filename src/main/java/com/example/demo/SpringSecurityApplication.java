package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;;

@SpringBootApplication
@ComponentScans({
	@ComponentScan("com.example1.demo.controller"),
	@ComponentScan("com.example.demo")
})
@EnableWebSecurity(debug = true)
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
