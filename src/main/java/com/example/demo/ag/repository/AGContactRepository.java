package com.example.demo.ag.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ag.model.AGContact;

//@Repository
public interface AGContactRepository extends MongoRepository<AGContact, String> {
	
	
}
