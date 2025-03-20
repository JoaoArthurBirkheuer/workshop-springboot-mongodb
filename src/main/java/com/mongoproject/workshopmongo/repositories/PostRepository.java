package com.mongoproject.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongoproject.workshopmongo.domain.Post;

// <TYPE OF CLASS TO MANAGE, TYPE OF ID OF CLASS>
@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	List<Post> findByTitleContaining(String text);
	// List<Post> findByTitleContainingIgnoreCase(String text);
	
	// HAS TO BE CHANGED IN THE POST SERVICE AS WELL
	
	@Query("{ 'title' : { $regex: ?0, $options : i } }")
	List<Post> searchByTitle(String text);
}
