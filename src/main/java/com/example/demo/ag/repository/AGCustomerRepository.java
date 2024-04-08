package com.example.demo.ag.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ag.model.AGCustomer;

//@Repository
public interface AGCustomerRepository extends MongoRepository<AGCustomer, Integer> {

	List<AGCustomer> findByEmail(String name);

//	List<AGCustomer> findByEmail(String email);

}
