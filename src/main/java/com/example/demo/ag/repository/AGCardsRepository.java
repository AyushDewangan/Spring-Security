package com.example.demo.ag.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ag.model.AGCards;

//@Repository
public interface AGCardsRepository extends MongoRepository<AGCards, Integer> {

	List<AGCards> findByCustomerId(int id);
	
//	List<AGCards> findByCustomerId(int customerId);

}
