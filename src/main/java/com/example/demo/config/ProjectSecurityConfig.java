package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.demo.filter.AuthoritiesLoggingAtFilter;
import com.example.demo.filter.AuthoritiesLoggingFilter;
import com.example.demo.filter.CsrfCookieFilter;
import com.example.demo.filter.JWTTokenGeneratorFilter;
import com.example.demo.filter.JWTTokenValidatorFilter;
import com.example.demo.filter.RequestValidationBeforeFilter;

import jakarta.servlet.http.HttpServletRequest;

/*
 * configuration annotation means while running the application
 * frameworks knows that here are some configuration
 * regarding project and chance to here are some beans also.
 */
@Configuration
//@EnableWebSecurity
public class ProjectSecurityConfig {

//	/*
//	 * UserDetails model used for use spring boot default UserDetails.
//	 * */
//	@Autowired
//	private UserDetailsServiceImpl userDetailsServiceImpl;
//	
//	/*
//	 * CustomerDetails model used for use custom model for authentication.
//	 * */
//	@Autowired
//	private CustomerDetailsServiceImpl customerDetailsServiceImpl;
//
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsServiceImpl);
//		auth.userDetailsService(customerDetailsServiceImpl);
//	}

	/* Configure for make public and private API's */
//	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		// CSRF token request
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");

		/* default code from SpringBootWebSecurityConfiguration */
		// http.authorizeHttpRequests((requests) ->
		// requests.anyRequest().authenticated());

		/* Here making API's private(authenticated()) and public(permitAll()) */
		http
		/* JWT section started to commented this code, below code means spring manage the jsessionId but in
		 * JWT we will manage by our self.*/
//		.securityContext((context) -> context.requireExplicitSave(false))
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
	
		//At Initial stage session will be state less
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		.cors().configurationSource(new CorsConfigurationSource() {
				.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
					// creating CORS configuration
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						/* Below line code is for JWT*/
						config.setExposedHeaders(Arrays.asList("Authorization"));
						config.setMaxAge(3600L);
						return config;
					}
				}))// .and()

				// If we disable CSRF then hacker easily able to hit our APIs.
				// .csrf().disable()

				// ignoring public API to CSRF disable.
				// .csrf().ignoringRequestMatchers("/ag/contact", "/ag/register")

				.csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
						.ignoringRequestMatchers("/ag/contact", "/ag/register", "/ag/save/myCards", "/ag/save/loan", "/ag/save/account", "/ag/save/balance", "/ag/save/authority", "/ag/notices/save")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				// After the BasicAuthentication completion CsrfCookieFilter executed
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
				.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new AuthoritiesLoggingFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests((requests) -> requests
//						.requestMatchers("/ag/myAccount", "/ag/myCards", "/ag/myBalance", "/ag/myLoans", "/ag/user").authenticated()
//						.requestMatchers("/ag/myAccount").hasAuthority("VIEWACCOUNT")
//						.requestMatchers("/ag/myCards").hasAuthority("VIEWCARDS")
//						.requestMatchers("/ag/myBalance").hasAuthority("VIEWBALANCE")
//						.requestMatchers("/ag/myLoans").hasAuthority("VIEWLOANS")
						.requestMatchers("/ag/myAccount").hasRole("USER")
						.requestMatchers("/ag/myCards").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/ag/myBalance").hasRole("ADMIN")
//						.requestMatchers("/ag/myLoans").hasRole("MANAGER")
						.requestMatchers("/ag/myLoans").hasRole("ADMIN")
						.requestMatchers("/ag/user").authenticated()
						.requestMatchers("/ag/notices", "/ag/contact", "/ag/register", "/ag/notices/save", "/ag/save/myCards", "/ag/save/loan", "/ag/save/account", "/ag/save/balance", "/ag/save/authority").permitAll());
		/* Configuration for denied all the access */
		// http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());

		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
//		.formLogin(Customizer.withDefaults())
//        .httpBasic(Customizer.withDefaults());
		return http.build();
	}

	/*
	 * Hashing the user send password for check with database stored hashed password
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* Below configuration for MySQL */
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

	/* Section 2 for authentication in InMemory */
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//
//		/*
//		 * Approach 1 where we use withDefaultPasswordEcoder() method while creating the
//		 * user detail
//		 */
//
////		UserDetails admin = User.withDefaultPasswordEncoder()
////				.username("admin")
////				.password("admin123")
////				.authorities("admin")
////				.build();
////
////		UserDetails user = User.withDefaultPasswordEncoder()
////				.username("user")
////				.password("user123")
////				.authorities("user")
////				.build();
//		
//		
//		/*
//		 * Approach 2 where we use NoOpPasswordEncoder bean while creating the
//		 * user detail
//		 */
//
//		UserDetails admin = User.withUsername("admin")
////				.username("admin")
//				.password("admin123")
//				.authorities("admin")
//				.build();
//
//		UserDetails user = User.withUsername("user")
////				.username("user")
//				.password("user123")
//				.authorities("user")
//				.build();
//
//		return new InMemoryUserDetailsManager(admin, user);
//	}

	/*
	 * NoOpPasswordEncoder is not used for production, Only used in non production
	 * phase
	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

}
