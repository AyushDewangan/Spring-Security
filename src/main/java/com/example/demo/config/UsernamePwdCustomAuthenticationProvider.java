package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.ag.model.AGAuthority;
import com.example.demo.ag.model.AGCustomer;
import com.example.demo.ag.repository.AGAuthorityRepository;
import com.example.demo.ag.repository.AGCustomerRepository;

@Component
public class UsernamePwdCustomAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private CustomerRepository customerRepository;

	@Autowired
	private AGCustomerRepository agCustomerRepository;
	
	@Autowired
	private AGAuthorityRepository agAuthorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		// Customer customer = customerRepository.findByEmail(username);
		AGCustomer customer = agCustomerRepository.findByEmail(username).get(0);
		Set<AGAuthority> agAuthority = agAuthorityRepository.findByCustomerId(customer.getId());
		if (customer != null) {
			if (passwordEncoder.matches(pwd, customer.getPwd())) {
//				List<GrantedAuthority> authorities = new ArrayList<>();
//				authorities.add(new SimpleGrantedAuthority(customer.getRole()));
//				return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
				return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(agAuthority));
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		} else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<AGAuthority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (AGAuthority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
