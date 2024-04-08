package com.example.demo.ag.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ag.model.AGLoans;

//@Repository
public interface AGLoanRepository extends MongoRepository<AGLoans, Integer> {

	List<AGLoans> findByCustomerIdOrderByStartDtDesc(int id);
	
//	List<AGLoans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
