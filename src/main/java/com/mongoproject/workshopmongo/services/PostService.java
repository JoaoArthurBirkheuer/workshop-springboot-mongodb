package com.mongoproject.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoproject.workshopmongo.domain.Post;
import com.mongoproject.workshopmongo.repositories.PostRepository;
import com.mongoproject.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository pr;
	
	public Post findById(String id) {
	    Optional<Post> obj = pr.findById(id);
	    return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
}
