package com.mongoproject.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongoproject.workshopmongo.domain.User;

// <TYPE OF CLASS TO MANAGE, TYPE OF ID OF CLASS>
@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
}
