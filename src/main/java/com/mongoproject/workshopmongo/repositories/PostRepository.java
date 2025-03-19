package com.mongoproject.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongoproject.workshopmongo.domain.Post;

// <TYPE OF CLASS TO MANAGE, TYPE OF ID OF CLASS>
@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
}
