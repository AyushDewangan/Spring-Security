package com.example.demo.ag.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ag.model.AGAccounts;

//@Repository
public interface AGAccountsRepository extends MongoRepository<AGAccounts, Long> {

	AGAccounts findByCustomerId(int id);
	
//	AGAccounts findByCustomerId(int customerId);

}
