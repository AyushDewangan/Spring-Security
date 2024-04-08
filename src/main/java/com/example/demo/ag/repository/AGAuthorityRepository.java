package com.example.demo.ag.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.ag.model.AGAuthority;

public interface AGAuthorityRepository extends MongoRepository<AGAuthority, Long> {

	Set<AGAuthority> findByCustomerId(int id);

}
