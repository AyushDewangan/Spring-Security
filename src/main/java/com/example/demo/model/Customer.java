package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {

	@Id
	private String id;
	private String email;
	private String pwd;
	private String role;

	public Customer() {
		super();
	}

	public Customer(String email, String pwd, String role) {
		this.email = email;
		this.pwd = pwd;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Customer{" + "id='" + id + '\'' + ", email='" + email + '\'' + ", pwd='" + pwd + '\'' + ", role='"
				+ role + '\'' + '}';
	}
}
