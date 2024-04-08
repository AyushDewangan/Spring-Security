package com.example.demo.ag.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.demo.ag.model.AGNotice;

//@Repository
//@EnableMongoRepositories
public interface AGNoticeRepository extends MongoRepository<AGNotice, Integer> {

//	List<AGNotice> findAllActiveNotices();
	
//	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
//	List<AGNotice> findAllActiveNotices();

}
