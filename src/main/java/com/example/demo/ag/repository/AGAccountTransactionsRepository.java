package com.example.demo.ag.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ag.model.AGAccountTransactions;

//@Repository
public interface AGAccountTransactionsRepository extends MongoRepository<AGAccountTransactions, String> {

	List<AGAccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int id);
	
//	List<AGAccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
