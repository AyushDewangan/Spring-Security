package com.example.demo.filter;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class AuthoritiesLoggingFilter implements Filter {
	private final Logger log = Logger.getLogger(AuthoritiesLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Authentication Validation in progress");
		chain.doFilter(request, response);
	}
	

}
